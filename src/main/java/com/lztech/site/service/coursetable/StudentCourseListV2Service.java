package com.lztech.site.service.coursetable;

import com.lztech.site.dto.StudentCourseQueryDTO;
import com.lztech.site.resource.course.CourseResourceStudentClassName;
import com.lztech.site.resource.course.CourseStudentResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 学生课程列表 V2 服务
 * 使用 EntityManager.createNativeQuery 实现高性能查询
 */
@Service
public class StudentCourseListV2Service {
    
    private static final Logger log = LoggerFactory.getLogger(StudentCourseListV2Service.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * 获取学生课程列表（优化版）
     * 
     * @param userId 学生ID
     * @param schoolYear 学年
     * @param term 学期
     * @param openId 微信openId
     * @return 课程列表
     */
    public ResponseEntity<List<CourseStudentResource>> getStudentCourseList(
            String userId, String schoolYear, Integer term, String openId) {
        
        try {
            // 1. 查询学生课程基本信息（仅关联 3 张表）
            List<StudentCourseQueryDTO> courseData = queryStudentCourses(userId, schoolYear, term, openId);
            
            if (courseData.isEmpty()) {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
            }
            
            // 2. 按课程分组
            Map<String, List<StudentCourseQueryDTO>> courseGroupMap = courseData.stream()
                    .collect(Collectors.groupingBy(StudentCourseQueryDTO::getCourseId));
            
            // 3. 批量查询班级人数
            Set<String> groupIds = courseData.stream()
                    .map(StudentCourseQueryDTO::getGroupId)
                    .collect(Collectors.toSet());
            Map<String, Integer> groupMemberCountMap = queryGroupMemberCount(groupIds);
            
            // 4. 批量查询教师信息（从 tb_course_table_detail_teacher 表获取）
            Set<String> courseTableIds = courseData.stream()
                    .map(StudentCourseQueryDTO::getCourseTableId)
                    .collect(Collectors.toSet());
            Map<String, List<TeacherInfo>> teacherMap = queryCourseTableTeachers(courseTableIds);
            
            // 5. 组装响应数据
            List<CourseStudentResource> resources = buildCourseStudentResources(
                    courseGroupMap, groupMemberCountMap, teacherMap);
            
            return new ResponseEntity<>(resources, HttpStatus.OK);
            
        } catch (Exception e) {
            log.error("getStudentCourseList error: userId={}, schoolYear={}, term={}, openId={}", 
                    userId, schoolYear, term, openId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 查询学生课程基本信息（核心优化查询）
     */
    private List<StudentCourseQueryDTO> queryStudentCourses(
            String userId, String schoolYear, Integer term, String openId) {
        
        // 构建动态 SQL（利用冗余字段，关联 4 张表）
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT ");
        sql.append("ct.id as courseTableId, ");
        sql.append("ct.course_id as courseId, ");
        sql.append("ct.course_name as courseName, ");
        sql.append("c.course_code as courseCode, ");
        sql.append("ct.college_name as collegeName, ");
        sql.append("cc.id as courseCategoryId, ");
        sql.append("cc.course_category_name as courseCategoryName, ");
        sql.append("g.id as groupId, ");
        sql.append("g.group_name as groupName, ");
        sql.append("g.group_no as groupNo, ");
        sql.append("g.sort as groupSort, ");
        sql.append("g.source as groupSource ");
        
        // 关联 4 张表（course_table + course + group + group_member + course_category 可选）
        sql.append("FROM tb_course_table ct ");
        sql.append("INNER JOIN tb_course c ON ct.course_id = c.id ");
        sql.append("INNER JOIN tb_group g ON ct.group_id = g.id ");
        sql.append("INNER JOIN tb_group_member gm ON g.id = gm.group_id ");
        sql.append("LEFT JOIN tb_course_category cc ON ct.course_category_id = cc.id ");
        
        // 动态 WHERE 条件
        List<String> conditions = new ArrayList<>();
        if (StringUtils.hasText(userId)) {
            conditions.add("gm.student_id = :userId");
        } else {
            conditions.add("gm.open_id = :openId");
        }
        conditions.add("ct.school_year = :schoolYear");
        conditions.add("ct.term = :term");
        conditions.add("g.group_status = 0");  // GroupStatus.NORMAL
        conditions.add("gm.group_member_status = 0");  // GroupMemberStatus.NORMAL
        
        sql.append("WHERE ").append(String.join(" AND ", conditions));
        sql.append(" ORDER BY cc.id, ct.course_id");
        
        // 创建查询并设置参数
        Query query = entityManager.createNativeQuery(sql.toString());
        
        if (StringUtils.hasText(userId)) {
            query.setParameter("userId", userId);
        } else {
            query.setParameter("openId", openId);
        }
        query.setParameter("schoolYear", schoolYear);
        query.setParameter("term", term);
        
        // 执行查询
        List<Object[]> results = query.getResultList();
        
        // 转换为 DTO
        return convertToDTO(results);
    }
    
    /**
     * 批量查询班级人数
     */
    private Map<String, Integer> queryGroupMemberCount(Set<String> groupIds) {
        if (groupIds.isEmpty()) {
            return Collections.emptyMap();
        }
        
        String sql = "SELECT group_id, COUNT(*) as member_count " +
                    "FROM tb_group_member " +
                    "WHERE group_id IN (:groupIds) " +
                    "AND group_member_status = 0 " +
                    "GROUP BY group_id";
        
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("groupIds", new ArrayList<>(groupIds));
        
        List<Object[]> results = query.getResultList();
        
        Map<String, Integer> countMap = new HashMap<>();
        for (Object[] row : results) {
            String groupId = (String) row[0];
            BigInteger count = (BigInteger) row[1];
            countMap.put(groupId, count.intValue());
        }
        
        return countMap;
    }
    
    /**
     * 批量查询课程表的教师信息（从 tb_course_table_detail_teacher 表获取）
     */
    private Map<String, List<TeacherInfo>> queryCourseTableTeachers(Set<String> courseTableIds) {
        if (courseTableIds.isEmpty()) {
            return Collections.emptyMap();
        }
        
        String sql = "SELECT ct.id as courseTableId, t.teacher_id, t.teacher_name " +
                    "FROM tb_course_table ct " +
                    "INNER JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id " +
                    "INNER JOIN tb_course_table_detail_teacher t ON ctd.id = t.course_table_detail_id " +
                    "WHERE ct.id IN (:courseTableIds) " +
                    "ORDER BY t.show_order";
        
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("courseTableIds", new ArrayList<>(courseTableIds));
        
        List<Object[]> results = query.getResultList();
        
        Map<String, List<TeacherInfo>> teacherMap = new HashMap<>();
        for (Object[] row : results) {
            String courseTableId = (String) row[0];
            String teacherId = (String) row[1];
            String teacherName = (String) row[2];
            
            TeacherInfo teacherInfo = new TeacherInfo(teacherId, teacherName);
            teacherMap.computeIfAbsent(courseTableId, k -> new ArrayList<>()).add(teacherInfo);
        }
        
        return teacherMap;
    }
    
    /**
     * 教师信息内部类
     */
    private static class TeacherInfo {
        private final String teacherId;
        private final String teacherName;
        
        public TeacherInfo(String teacherId, String teacherName) {
            this.teacherId = teacherId;
            this.teacherName = teacherName;
        }
        
        public String getTeacherId() {
            return teacherId;
        }
        
        public String getTeacherName() {
            return teacherName;
        }
    }
    
    /**
     * 将查询结果转换为 DTO
     */
    private List<StudentCourseQueryDTO> convertToDTO(List<Object[]> results) {
        List<StudentCourseQueryDTO> dtoList = new ArrayList<>();
        
        for (Object[] row : results) {
            StudentCourseQueryDTO dto = new StudentCourseQueryDTO();
            dto.setCourseTableId((String) row[0]);
            dto.setCourseId((String) row[1]);
            dto.setCourseName((String) row[2]);
            dto.setCourseCode((String) row[3]);
            dto.setCollegeName((String) row[4]);
            
            // 处理可能为 null 的 Integer 类型
            if (row[5] != null) {
                dto.setCourseCategoryId(((Number) row[5]).intValue());
            }
            
            dto.setCourseCategoryName((String) row[6]);
            dto.setGroupId((String) row[7]);
            dto.setGroupName((String) row[8]);
            dto.setGroupNo((String) row[9]);
            
            if (row[10] != null) {
                dto.setGroupSort(((Number) row[10]).intValue());
            }
            
            if (row[11] != null) {
                dto.setGroupSource(((Number) row[11]).intValue());
            }
            
            dtoList.add(dto);
        }
        
        return dtoList;
    }
    
    /**
     * 构建课程学生资源响应
     */
    private List<CourseStudentResource> buildCourseStudentResources(
            Map<String, List<StudentCourseQueryDTO>> courseGroupMap,
            Map<String, Integer> groupMemberCountMap,
            Map<String, List<TeacherInfo>> teacherMap) {
        
        List<CourseStudentResource> resources = new ArrayList<>();
        
        courseGroupMap.forEach((courseId, courseDataList) -> {
            CourseStudentResource resource = new CourseStudentResource();
            
            // 基本信息（取第一条记录）
            StudentCourseQueryDTO firstData = courseDataList.get(0);
            resource.setCourseId(firstData.getCourseId());
            resource.setCourseCode(firstData.getCourseCode());
            resource.setCourseName(firstData.getCourseName());
            resource.setCollegeName(firstData.getCollegeName());
            resource.setCourseCategoryId(firstData.getCourseCategoryId());
            resource.setCourseCategory(firstData.getCourseCategoryName());
            
            // 处理教师信息（从 teacherMap 中获取，按 courseTableId 分组）
            Set<String> teacherIdSet = new LinkedHashSet<>();
            Set<String> teacherNameSet = new LinkedHashSet<>();
            
            for (StudentCourseQueryDTO data : courseDataList) {
                List<TeacherInfo> teachers = teacherMap.get(data.getCourseTableId());
                if (teachers != null) {
                    for (TeacherInfo teacher : teachers) {
                        if (StringUtils.hasText(teacher.getTeacherId())) {
                            teacherIdSet.add(teacher.getTeacherId());
                        }
                        if (StringUtils.hasText(teacher.getTeacherName())) {
                            teacherNameSet.add(teacher.getTeacherName());
                        }
                    }
                }
            }
            
            // 设置教师 ID（逗号分隔）
            resource.setTeacherId(String.join(",", teacherIdSet));
            
            // 设置教师姓名（顿号分隔）
            resource.setTeacherName(String.join("、", teacherNameSet));
            
            // 处理班级列表
            List<CourseResourceStudentClassName> teachingClasses = courseDataList.stream()
                    .map(data -> {
                        CourseResourceStudentClassName className = new CourseResourceStudentClassName();
                        className.setClassId(data.getGroupId());
                        className.setClassName(data.getGroupName());
                        className.setClassNo(data.getGroupNo());
                        className.setClassSort(data.getGroupSort() != null ? data.getGroupSort() : 0);
                        className.setClassType(data.getGroupSource());
                        
                        // 设置班级人数
                        Integer memberCount = groupMemberCountMap.getOrDefault(data.getGroupId(), 0);
                        className.setClassMembers(memberCount);
                        
                        return className;
                    })
                    .distinct()
                    .collect(Collectors.toList());
            
            // 排序班级列表
            List<CourseResourceStudentClassName> sortedTeachingClasses = teachingClasses.stream()
                    .sorted(Comparator.comparingInt(CourseResourceStudentClassName::getClassSort)
                            .thenComparing((CourseResourceStudentClassName obj) ->
                                    (obj.getClassNo() != null && obj.getClassNo().startsWith("2")) ? 0 : 1)
                            .thenComparing(CourseResourceStudentClassName::getClassNo))
                    .collect(Collectors.toList());
            
            resource.setTeachingClasses(sortedTeachingClasses);
            resources.add(resource);
        });
        
        // 排序课程列表
        resources.sort(Comparator.comparing(CourseStudentResource::getCourseCategoryId,
                        Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(CourseStudentResource::getCourseId));
        
        return resources;
    }
}
