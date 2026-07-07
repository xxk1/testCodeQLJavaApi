package com.lztech.site.service.group;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.lztech.domain.model.classgroupingscheme.enums.SchemeType;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetable.enums.WeekType;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetail.enums.CourseStatus;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberSource;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.term.Term;
import com.lztech.persistence.repositories.classgroupingscheme.ClassGroupingSchemeRepository;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetail.CourseTableDetailRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.group.specification.GroupSpecification;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.persistence.repositories.groupmember.specification.GroupMemberSpecification;
import com.lztech.site.constants.Constant;
import com.lztech.site.resource.classresource.ClassResource;
import com.lztech.site.resource.group.*;
import com.lztech.site.resource.teachingclass.TeachingClassResource;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.coursetable.CourseTableService;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.service.event.EventService;
import com.lztech.site.service.term.TermService;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.DateUtils;
import com.lztech.site.viewmodel.CourseResource;
import com.lztech.site.viewmodel.CourseResourceTeachingClassName;
import com.lztech.site.viewmodel.StudentInfo;
import com.lztech.site.viewmodel.authorityapi.TeacherVo;
import com.lztech.site.viewmodel.errorresult.ErrorResult;
import com.lztech.site.viewmodel.event.GroupEvent;
import com.lztech.site.viewmodel.group.*;
import com.lztech.site.viewmodel.term.TermResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.persistence.repositories.coursetabledetail.specification.CourseTableDetailSpecification.getTeacherCourseSpecification;
import static com.lztech.persistence.repositories.group.specification.GroupSpecification.getTeacherGroupSpecification;

@Service
public class GroupService {

    private static int three = 3, twenty = 20, ten = 10;

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private CourseTableRepository courseTableRepository;

    @Autowired
    private CourseTableDetailRepository courseTableDetailRepository;

    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private ClassGroupingSchemeRepository classGroupingSchemeRepository;
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CourseTableService courseTableService;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TermService termService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AuthorityApiService authorityApiService;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseTableDetailService courseTableDetailService;

    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Value("${studentType}")
    private String studentType;
    @Value("${default.defaultStudentType}")
    private String defaultStudentType;

    public ResponseEntity<ClassResource> updateGroupBaseInfo(String userId, String userName,
                                                             String groupId, String groupName,
                                                             String classNickName, Integer groupAttribute) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClassResource classResource = new ClassResource();
        group.setModifierId(userId);
        group.setModifierName(userName);
        group.setModifyTime(new Date());
        if (!StringUtils.isEmpty(classNickName)) {
            group.setClassNickName(classNickName);
        }
        if (!StringUtils.isEmpty(groupName)) {
            group.setGroupName(groupName);
        }
        group.setGroupAttribute(GroupAttribute.getGroupAttribute(groupAttribute));
        Group save = groupRepository.save(group);
        if (save != null) {
            classResource.setClassName(save.getGroupName());
            classResource.setClassAttribute(Objects.isNull(save.getGroupAttribute()) ? null : save.getGroupAttribute().getValue());
            if (groupAttribute == GroupAttribute.THEORY.getValue()) {
                // 删除实验小组方案
                classGroupingSchemeRepository.deleteByGroupIdAndSchemeType(groupId, SchemeType.EXPERIMENTAL_GROUP_SCHEME);
            }
            if (dataVisualEnable) {
                GroupEvent groupEvent = eventService.buildGroupEvent(group);
                eventService.sendGroupEvent(groupEvent.getId(), groupEvent);
            }
        }
        return new ResponseEntity<>(classResource, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteGroup(String groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (Source.DATA_DOCKING.equals(group.getSource())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        group.setGroupStatus(GroupStatus.REMOVE);
        changeGroupMemberStatus(group);
        if (dataVisualEnable) {
            eventService.sendDeleteGroupEvent(group.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void changeGroupMemberStatus(Group group) {
        List<GroupMember> groupMembers = new ArrayList<>();
        for (GroupMember groupMember : groupMemberRepository.findByGroup(group)) {
            groupMember.setGroupMemberStatus(GroupMemberStatus.REMOVE);
            groupMembers.add(groupMember);
        }
        groupMemberRepository.saveAll(groupMembers);
    }

    public ResponseEntity<GroupStudentPageResource> getGroupMembers(String groupIds, String userNoOrUserName,
                                                                    Integer page, Integer pageSize) {
        int total;
        List<String> groupId = Arrays.asList(groupIds.split(","));
        List<StudentResource> studentResources = new ArrayList<>();
        if (page == null || pageSize == null) {
            List<GroupMember> groupMembers = groupMemberRepository.findAll(
                    GroupMemberSpecification.specification(groupId, userNoOrUserName));
            groupMembers.forEach(groupMember -> studentResources.add(getStudentResource(groupMember)));
            total = groupMembers.size();
        } else {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            Page<GroupMember> groupMemberPage = groupMemberRepository.findAll(
                    GroupMemberSpecification.specification(groupId, userNoOrUserName), pageable);
            groupMemberPage.getContent().forEach(groupMember -> studentResources.add(getStudentResource(groupMember)));
            total = (int) groupMemberPage.getTotalElements();
        }
        return new ResponseEntity<>(getGroupStudentPageResource(total, page, pageSize, studentResources), HttpStatus.OK);
    }

    private StudentResource getStudentResource(GroupMember groupMember) {
        StudentResource studentResource = new StudentResource();
        studentResource.setClassId(groupMember.getGroup().getId());
        studentResource.setClassName(groupMember.getGroup().getGroupName());
        studentResource.setUserId(groupMember.getStudentId());
        studentResource.setUserName(groupMember.getStudentName());
        studentResource.setUserNo(groupMember.getStudentNo());
        studentResource.setOpenId(groupMember.getOpenId());
        studentResource.setClassNickName(groupMember.getGroup().getClassNickName());
        studentResource.setSource(groupMember.getGroup().getSource().getIndex());
        studentResource.setCreateTime(DateUtils.formatDate(DateUtils.DATE_TIME, groupMember.getCreateTime()));
        studentResource.setAdministrativeClassName(
                org.apache.commons.lang3.StringUtils.isBlank(groupMember.getAdministrativeClassName())
                        ? "" : groupMember.getAdministrativeClassName());
        studentResource.setStudentCollegeId(org.apache.commons.lang3.StringUtils.isBlank(groupMember.getStudentCollegeId())
                ? "" : groupMember.getStudentCollegeId());
        studentResource.setStudentCollegeName(org.apache.commons.lang3.StringUtils.isBlank(groupMember.getStudentCollegeName())
                ? "" : groupMember.getStudentCollegeName());
        return studentResource;
    }

    private GroupStudentPageResource getGroupStudentPageResource(int total, Integer page, Integer pageSize,
                                                                 List<StudentResource> studentResources) {
        GroupStudentPageResource groupStudentPageResource = new GroupStudentPageResource();
        groupStudentPageResource.setTotal(total);
        groupStudentPageResource.setPage(page);
        groupStudentPageResource.setPageSize(pageSize);
        groupStudentPageResource.setStudents(studentResources);
        return groupStudentPageResource;
    }


    public ResponseEntity<GroupDetails> createGroup(CreateGroup createGroup) {
        Course course = courseRepository.getOne(createGroup.getCourseId());
        if (course == null) {
            return new ResponseEntity(ErrorResult.customError("当前课程信息不存在"), HttpStatus.OK);
        }

        List<CourseTableDetail> courseTableDetailList = courseTableDetailRepository
                .findAll(getTeacherCourseSpecification(createGroup.getTeacherId(), createGroup.getCourseId()));
        if (courseTableDetailList.size() == 0) {
            return new ResponseEntity(ErrorResult.customError("当前人员没有课表信息，无法创建班级"), HttpStatus.OK);
        }
        if (!studentType.equals("-1")) {
            courseTableDetailList = courseTableDetailList.stream()
                    .filter(item -> item.getCourseTable().getStudentType().getIndex().equals(Integer.parseInt(studentType)))
                    .collect(Collectors.toList());
        }

        Term term = termService.getNowTerm();
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        String resultMonth = month < ten ? "0" + month : String.valueOf(month);
        String resultDate = date < ten ? "0" + date : String.valueOf(date);
        String groupNoPrefix = term.getSchoolYear().replace("-", "") + resultMonth + resultDate;
        Group group = groupRepository.findByGroupNoLike(groupNoPrefix);
        int startNum = 0;
        if (Objects.nonNull(group)) {
            try {
                startNum = Integer.parseInt(group.getGroupNo().substring(group.getGroupNo().length() - three, group.getGroupNo().length()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Group saveGroup = getSaveGroup(createGroup, groupNoPrefix, startNum);
        CourseTable saveCourseTable = getSaveCourseTable(createGroup, course, courseTableDetailList, saveGroup);
        CourseTableDetail saveCourseTableDetail = getSaveCourseTableDetail(createGroup, saveCourseTable);
        getSaveCourseTableDetailTeacher(createGroup, saveCourseTableDetail);
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setClassId(saveGroup.getId());
        groupDetails.setClassName(saveGroup.getGroupName());
        groupDetails.setClassSize("0");
        groupDetails.setCourseId(course.getId());
        groupDetails.setCourseTableDetailId(saveCourseTableDetail.getId());
        groupDetails.setCourseTableId(saveCourseTable.getId());
        if (dataVisualEnable) {
            GroupEvent groupEvent = eventService.buildGroupEvent(group);
            eventService.sendGroupEvent(groupEvent.getId(), groupEvent);
        }
        return new ResponseEntity(groupDetails, HttpStatus.OK);
    }

    private void getSaveCourseTableDetailTeacher(CreateGroup createGroup, CourseTableDetail saveCourseTableDetail) {
        CourseTableDetailTeacher courseTableDetailTeacher = new CourseTableDetailTeacher();
        courseTableDetailTeacher.setCourseTableDetail(saveCourseTableDetail);
        courseTableDetailTeacher.setTeacherId(createGroup.getTeacherId());
        courseTableDetailTeacher.setTeacherNo(createGroup.getTeacherNo());
        courseTableDetailTeacher.setCreatorId(createGroup.getTeacherId());
        courseTableDetailTeacher.setCreatorName(createGroup.getTeacherName());
        courseTableDetailTeacher.setCreateTime(new Date());
        courseTableDetailTeacher.setTeacherName(createGroup.getTeacherName());
        courseTableDetailTeacher.setTeacherCollegeName(createGroup.getCollegeName());
        courseTableDetailTeacher.setTeacherCollegeId(createGroup.getCollegeId());
        courseTableDetailTeacherRepository.save(courseTableDetailTeacher);
    }

    private CourseTableDetail getSaveCourseTableDetail(CreateGroup createGroup, CourseTable saveCourseTable) {
        CourseTableDetail courseTableDetail = new CourseTableDetail();
        courseTableDetail.setCreatorId(createGroup.getTeacherId());
        courseTableDetail.setCreateTime(new Date());
        courseTableDetail.setCreatorName(createGroup.getTeacherName());
        courseTableDetail.setSource(Source.AUTONOMOUS_CLASS);
        courseTableDetail.setCourseTable(saveCourseTable);
        courseTableDetail.setCourseStatus(CourseStatus.NOT_CLASS);
        return courseTableDetailRepository.save(courseTableDetail);
    }

    private CourseTable getSaveCourseTable(CreateGroup createGroup, Course course, List<CourseTableDetail> courseTableDetailList, Group saveGroup) {
        CourseTable courseTable = new CourseTable();
        courseTable.setCreatorId(createGroup.getTeacherId());
        courseTable.setCreatorName(createGroup.getTeacherName());
        courseTable.setCreateTime(new Date());
        courseTable.setCourse(course);
        courseTable.setCourseName(course.getCourseName());
        courseTable.setCreateTime(new Date());
        courseTable.setTeacherName(createGroup.getTeacherName());
        courseTable.setSchoolYear(createGroup.getSchoolYear());
        courseTable.setTerm(Integer.valueOf(createGroup.getTerm()));
        courseTable.setSource(Source.AUTONOMOUS_CLASS);
        courseTable.setCourseCategory(courseTableDetailList.get(0).getCourseTable().getCourseCategory());
        courseTable.setCollegeId(course.getCollege() == null ? null : course.getCollege().getId());
        courseTable.setCollegeName(course.getCollege() == null ? null : course.getCollege().getCollegeName());
        courseTable.setWeekType(WeekType.ONE_OR_TWO_WEEKS);
        courseTable.setGroup(saveGroup);
        if (StringUtils.isEmpty(createGroup.getStudentType())) {
            courseTable.setStudentType(StudentType.getStudentType(Integer.parseInt(defaultStudentType)));
        } else {
            courseTable.setStudentType(StudentType.getStudentType(Integer.parseInt(createGroup.getStudentType())));
        }
        return courseTableRepository.save(courseTable);
    }

    private Group getSaveGroup(CreateGroup createGroup,
                               String groupNoPrefix,
                               int startNum) {
        Group group = new Group();
        Integer term = Integer.parseInt(createGroup.getTerm());
        List<CourseResource> courseTableResource =
                courseTableService.getCourseTableResource(createGroup.getCourseId(), createGroup.getSchoolYear(), term, createGroup.getTeacherId());
        if (!CollectionUtils.isEmpty(courseTableResource)) {
            CourseResource courseResource = courseTableResource.get(0);
            List<CourseResourceTeachingClassName> teachingClassName = courseResource.getTeachingClassName();

            group.setSort(teachingClassName.size() + Constant.ONE);
        } else {
            group.setSort(Constant.ZREO);
        }
        group.setId(UUID.randomUUID().toString());
        group.setCreatorId(createGroup.getTeacherId());
        group.setCreatorName(createGroup.getTeacherName());
        group.setModifierId(createGroup.getTeacherId());
        group.setModifierName(createGroup.getTeacherName());
        group.setCreateTime(new Date());
        group.setModifyTime(new Date());
        group.setGroupName(createGroup.getClassName());
        group.setGroupAttribute(GroupAttribute.getGroupAttribute(createGroup.getGroupAttribute()));
        startNum = startNum + 1;
        String groupNo = groupNoPrefix + String.format("%03d", startNum);
        group.setGroupNo(groupNo);
        group.setGroupStatus(GroupStatus.NORMAL);
        group.setSource(Source.AUTONOMOUS_CLASS);
        group.setClassNickName(group.getGroupName());
        return groupRepository.save(group);
    }


    public void saveGroupsAndMembers(List<GroupResource> groupResourceList) {
        List<Group> groupList = new ArrayList<>();
        List<GroupMember> groupMemberList = new ArrayList<>();
        groupResourceList.forEach(groupResource -> {
            Group group = groupRepository.findById(groupResource.getGroupNo()).orElse(null);
            if (Objects.isNull(group)) {
                group = new Group();
                group.setGroupName(groupResource.getGroupName());
                group.setGroupNo(groupResource.getGroupNo());
                group.setId(groupResource.getGroupNo());
                group.setSource(Source.DATA_DOCKING);
                group.setGroupStatus(GroupStatus.NORMAL);
                group.setCreateTime(new Date());
                group.setModifyTime(new Date());
                for (GroupMemberResource groupMember : groupResource.getGroupMembers()) {
                    groupMemberList.add(createGroupMember(group, groupMember));
                }
            } else {
                // 组存在表示成员数量有变化，更新组成员
                groupMemberList.addAll(updateGroupMember(group, group.getGroupMemberList(), groupResource.getGroupMembers()));
            }

            groupList.add(group);
        });
        List<Group> savedGroups = groupRepository.saveAll(groupList);
        if (savedGroups != null) {
            groupMemberRepository.saveAll(groupMemberList);
        }
    }

    private List<GroupMember> updateGroupMember(Group group, List<GroupMember> groupMemberList, List<GroupMemberResource> groupMembers) {
        List<GroupMember> resultGroupMemberList = new ArrayList<>();
        groupMembers.forEach(groupMemberResource -> {
            GroupMember groupMember = groupMemberList
                    .stream()
                    .filter(mem -> mem.getStudentId().equals(groupMemberResource.getStudentId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(groupMember)) {
                resultGroupMemberList.add(createGroupMember(group, groupMemberResource));
            }
        });
        return resultGroupMemberList;
    }

    private GroupMember createGroupMember(Group group, GroupMemberResource groupMemberResource) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGroup(group);
        groupMember.setGroupMemberStatus(GroupMemberStatus.NORMAL);
        groupMember.setStudentId(groupMemberResource.getStudentId());
        groupMember.setStudentName(groupMemberResource.getStudentName());
        groupMember.setStudentNo(groupMemberResource.getStudentNo());
        groupMember.setAdministrativeClassName(groupMemberResource.getClassName());
        groupMember.setCreateTime(new Date());
        groupMember.setModifyTime(new Date());
        groupMember.setSource(GroupMemberSource.DATA_DOCKING);
        return groupMember;
    }

    public List<GroupResource> getAllGroup() {
        List<Group> groupList = groupRepository.findAll();
        List<GroupResource> groupResourceList = groupList.stream().map(group -> {
            GroupResource groupResource = new GroupResource();
            groupResource.setGroupNo(group.getGroupNo());
            groupResource.setGroupName(group.getGroupName());
            groupResource.setGroupMembersNum(new BigDecimal(group.getGroupMemberList().size()));
            return groupResource;
        }).collect(Collectors.toList());
        return groupResourceList;

    }

    public ResponseEntity<List<StudentInfo>> getGroupMember(String groupId) {
        List<StudentInfo> studentInfoList = new ArrayList<>();
        List<GroupMember> groupMemberList = groupMemberRepository.findByStudentByGroupId(groupId);
        for (GroupMember member : groupMemberList) {
            StudentInfo info = new StudentInfo();
            info.setClassName(member.getAdministrativeClassName());
            info.setStudentNo(member.getStudentNo());
            info.setStudentId(member.getStudentId());
            info.setStudentName(member.getStudentName());
            info.setopenId(member.getOpenId());
            studentInfoList.add(info);
        }
        return new ResponseEntity(studentInfoList, HttpStatus.OK);
    }

    public ResponseEntity<GroupVo> getGroupVos(String groupNo, String groupName, String creatorName, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Group> listGroups = groupRepository.findAll(GroupSpecification.getGroupInfoSpecification(groupNo, groupName, creatorName), pageable);
        List<GroupResourceVo> list = listGroups.stream().map(group -> {
            GroupResourceVo vo = new GroupResourceVo();
            vo.setGroupNo(group.getGroupNo());
            vo.setCreateName(group.getCreatorName());
            vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(group.getCreateTime()));
            vo.setGroupId(group.getId());
            vo.setModifierId(group.getModifierId());
            vo.setModifierName(group.getModifierName());
            vo.setModifierTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(group.getModifyTime()));
            vo.setGroupName(group.getGroupName());
            vo.setSource(group.getSource().getSourceName());
            return vo;
        }).collect(Collectors.toList());
        GroupVo groupVo = new GroupVo();
        groupVo.setTotal((int) listGroups.getTotalElements());
        groupVo.setPageSize(pageSize);
        groupVo.setPage(page);
        groupVo.setPageCount(listGroups.getTotalPages());
        groupVo.setGroupResourcesList(list);
        return new ResponseEntity<>(groupVo, HttpStatus.OK);

    }

    public ResponseEntity<List<TeachingClassResource>> findGroupsByTeacher(String teacherId, String schoolYear, Integer term, String courseId) {
        List<Group> groupList = groupRepository.findAll(getTeacherGroupSpecification(teacherId, schoolYear, term, courseId));
        if (CollectionUtils.isEmpty(groupList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<TeachingClassResource> teachingClassResourceList = createTeachingClassResources(groupList);
        return new ResponseEntity<>(teachingClassResourceList, HttpStatus.OK);
    }

    private List<TeachingClassResource> createTeachingClassResources(List<Group> groupList) {
        List<TeachingClassResource> teachingClassResourceList = new ArrayList<>();
        groupList.sort(Comparator.comparing(Group::getCreateTime).thenComparing(Group::getId));
        groupList.forEach(group -> {
            TeachingClassResource teachingClassResource = new TeachingClassResource();
            teachingClassResource.setTeachingClassId(group.getId());
            teachingClassResource.setTeachingClassName(group.getGroupName());
            teachingClassResource.setTeachingClassType(group.getSource().getIndex());
            teachingClassResource.setTeachingClassCode(group.getGroupNo());
            Integer sort = ObjectUtils.isEmpty(group.getSort()) ? 0 : group.getSort();
            teachingClassResource.setTeachingClassSort(sort);
            teachingClassResource.setClassNickName(group.getClassNickName());
            teachingClassResourceList.add(teachingClassResource);
        });
        return teachingClassResourceList.stream()
                .sorted(Comparator.comparingInt(TeachingClassResource::getTeachingClassSort)
                        .thenComparing((TeachingClassResource obj) ->
                                (obj.getTeachingClassCode() != null && obj.getTeachingClassCode().startsWith("2")) ? 0 : 1)
                        .thenComparing(TeachingClassResource::getTeachingClassCode))
                .collect(Collectors.toList());
    }

    //region 根据课程Id查询班级信息
    public ResponseEntity<List<GroupSimpleInfo>> findGroupsByCourseId(String courseId, Integer term, String schoolYear, Integer courseStudentType) {
        List<CourseTable> courseTables = this.courseTableRepository.findByCourseIdAndTermAndSchoolYear(courseId, term, schoolYear);

        if (Objects.nonNull(courseStudentType) && courseStudentType != -1) {
            courseTables = courseTables.stream()
                    .filter(item -> item.getStudentType().getIndex().equals(courseStudentType))
                    .collect(Collectors.toList());
        }
        if (courseTables != null && !courseTables.isEmpty()) {
            List<GroupSimpleInfo> groupSimpleInfos = courseTables.stream()
                    .filter(x -> ObjectUtils.isNotEmpty(x.getGroup()) && x.getGroup().getGroupStatus() == GroupStatus.NORMAL)
                    .map(courseTable -> new GroupSimpleInfo() {
                                {
                                    this.setGroupId(courseTable.getGroup().getId());
                                    this.setGroupName(courseTable.getGroup().getGroupName());
                                    this.setGroupNo(courseTable.getGroup().getGroupNo());
                                    if (org.apache.commons.lang3.StringUtils.isNotBlank(courseTable.getTeacherName())) {
                                        String[] teacherNames = courseTable.getTeacherName().split(",");
                                        Set<String> uniqueTeacherNames = new HashSet<>(Arrays.asList(teacherNames));
                                        this.setTeacherNames(String.join(",", uniqueTeacherNames));
                                    } else {
                                        this.setTeacherNames("");
                                    }
                                }
                            }
                    ).collect(Collectors.toList());
            groupSimpleInfos = groupSimpleInfos.stream().distinct().collect(Collectors.toList());
            return new ResponseEntity<>(groupSimpleInfos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<GroupVoResourcePage> getGroups(GroupParam groupParam) {
        GroupVoResourcePage groupVoResourcePage = new GroupVoResourcePage();
        List<GroupVoResource> groupVoResourceList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        String sql = getSql(groupParam, paramMap);
        int start = (groupParam.getPage() - 1) * groupParam.getPageSize();
        int size = groupParam.getPageSize();
        sql += " limit " + start + "," + size;
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<GroupDetail> groupDetailList = JSONArray.parseArray(com.alibaba.fastjson.JSONObject.toJSON(query.getResultList()).toString(),
                GroupDetail.class);
        List<String> groupIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(groupDetailList)) {
            groupIdList = groupDetailList.stream().map(GroupDetail::getGroupId).collect(Collectors.toList());
        }
        List<GroupMember> groupMemberList = groupMemberRepository.findByGroupIdInAndGroupMemberStatus(groupIdList, GroupMemberStatus.NORMAL);

        for (GroupDetail groupDetail : groupDetailList) {
            GroupVoResource groupVoResource = new GroupVoResource();
            List<GroupMember> groupMembers = groupMemberList.stream()
                    .filter(groupMember -> groupMember.getGroup().getId().equals(groupDetail.getGroupId())).collect(Collectors.toList());

            groupVoResource.setCourseName(groupDetail.getCourseName());
            groupVoResource.setModifierName(groupDetail.getModifierName());
            groupVoResource.setGroupName(groupDetail.getGroupName());
            groupVoResource.setGroupNo(groupDetail.getGroupNo());
            groupVoResource.setGroupId(groupDetail.getGroupId());
            groupVoResource.setSchoolYear(groupParam.getSchoolYear());
            groupVoResource.setTerm(groupParam.getTerm());

            com.lztech.site.resource.term.TermResource termResource = termService.getTerm(groupParam.getSchoolYear(),groupParam.getTerm()+"");
            if (StringUtils.isEmpty(termResource.getSchoolYearTermNickName())){
                groupVoResource.setSchoolYearTermNickName(groupParam.getSchoolYear()+"-"+groupParam.getTerm());
            }else {
                groupVoResource.setSchoolYearTermNickName(termResource.getSchoolYearTermNickName());
            }

            groupVoResource.setClassCompositionName(groupDetail.getClassCompositionName());

            if (groupDetail.getSource() == 0) {
                groupVoResource.setSource("数据对接");
            } else {
                groupVoResource.setSource("手动添加");
            }
            groupVoResource.setTeacherName(groupDetail.getTeacherName());
            if (CollectionUtils.isNotEmpty(groupMembers)) {
                groupVoResource.setGroupMembersNum(groupMembers.size());
            } else {
                groupVoResource.setGroupMembersNum(0);
            }
            groupVoResource.setCollegeName(groupDetail.getCollegeName());
            groupVoResource.setCourseCategoryName(groupDetail.getCourseCategoryName());
            groupVoResourceList.add(groupVoResource);
        }
        Integer count = getCountSql(groupParam);
        groupVoResourcePage.setTotal(count);
        groupVoResourcePage.setPage(groupParam.getPage());
        groupVoResourcePage.setPageSize(groupParam.getPageSize());
        groupVoResourcePage.setGroupVoResourceList(groupVoResourceList);


        return new ResponseEntity<>(groupVoResourcePage, HttpStatus.OK);
    }

    private Integer getCountSql(GroupParam groupParam) {
        String sql = "SELECT count(1) as total FROM (SELECT count(1) " +
                " FROM tb_course_table a left JOIN tb_course d on a.course_id = d.id left JOIN tb_course_table_detail b on a.id=b.course_table_id  " +
                " left join tb_course_table_detail_teacher c on b.id = c.course_table_detail_id left JOIN tb_group tg on a.group_id = tg.id " +
                " LEFT JOIN tb_course_category cc ON cc.id = a.course_category_id " +
                " where d.use_state=1 and a.school_year = '" + groupParam.getSchoolYear() + "' and a.term = '" + groupParam.getTerm() +
                "' and tg.group_status = 0";
        Map<String, Object> paramMap = new HashMap<>();
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and a.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }
        if (groupParam.getSource() != null) {
            if (groupParam.getSource() == 0) {
                sql += " and a.source = '0'";
            } else {
                sql += " and a.source != '0'";
            }
        }
        if (!StringUtils.isEmpty(groupParam.getCourseName())) {
            String courseName = "%" + CharactersReplace.replaceCharacters(groupParam.getCourseName().trim()) + "%";
            sql += " and a.course_name like :courseName";
            paramMap.put("courseName", courseName);
        }
        if (!StringUtils.isEmpty(groupParam.getGroupName())) {
            String groupName = "%" + CharactersReplace.replaceCharacters(groupParam.getGroupName().trim()) + "%";
            sql += " and tg.group_name like :groupName";
            paramMap.put("groupName", groupName);
        }

        if (!StringUtils.isEmpty(groupParam.getCourseNameOrCode())) {
            String courseNameOrCode = "%" + CharactersReplace.replaceCharacters(groupParam.getCourseNameOrCode().trim()) + "%";
            sql += " and ( d.course_code like :courseNameOrCode or d.course_name like :courseNameOrCode )";
            paramMap.put("courseNameOrCode", courseNameOrCode);
        }

        if (!StringUtils.isEmpty(groupParam.getTeacherNameOrCode())) {
            String teacherNameOrCode = "%" + CharactersReplace.replaceCharacters(groupParam.getTeacherNameOrCode().trim()) + "%";
            sql += " and ( c.teacher_name like :teacherNameOrCode or c.teacher_no like :teacherNameOrCode )";
            paramMap.put("teacherNameOrCode", teacherNameOrCode);
        }

        if (!StringUtils.isEmpty(groupParam.getCourseCategoryId())) {
            sql = sql + " and  cc.id like :courseCategoryId ";
            paramMap.put("courseCategoryId", groupParam.getCourseCategoryId());
        }

        if (!StringUtils.isEmpty(groupParam.getCollegeId())) {
            sql = sql + " and  a.college_id like :collegeId ";
            paramMap.put("collegeId", groupParam.getCollegeId());
        }

        if (groupParam.isPublicStatus() != null) {
            if (groupParam.getGroupIds() != null && !groupParam.getGroupIds().isEmpty() && groupParam.isPublicStatus()) {
                sql = sql + " and  tg.id in (:groupIdList)";
                paramMap.put("groupIdList", groupParam.getGroupIds());
            }
            if (groupParam.getGroupIds() != null && !groupParam.getGroupIds().isEmpty() && !groupParam.isPublicStatus()) {
                sql = sql + " and  tg.id not in (:groupIdList)";
                paramMap.put("groupIdList", groupParam.getGroupIds());
            }
        }

        sql += " GROUP BY a.group_id ) count ";
        Query queryCount = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryCount::setParameter);
        queryCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> countMap = (Map<String, Object>) queryCount.getResultList().get(0);
        return ((BigInteger) countMap.get("total")).intValue();
    }


    private String getSql(GroupParam groupParam, Map<String, Object> paramMap) {
        String sql = "SELECT a.group_id as groupId,a.source ,tg.group_no as groupNo,tg.group_name as groupName," +
                " GROUP_CONCAT( distinct c.teacher_name,'/',c.teacher_no SEPARATOR '、') as teacherName,tg.source as classSource," +
                " tg.class_composition_name as classCompositionName,cc.course_category_name as courseCategoryName," +
                " cc.id as courseCategoryId,a.college_name as collegeName ," +
                " tg.modifier_name as modifierName,GROUP_CONCAT( distinct d.course_name,'(',d.course_code,')')  as courseName " +
                " FROM tb_course_table a left JOIN tb_course d on a.course_id = d.id left JOIN tb_course_table_detail b on a.id=b.course_table_id  " +
                " left join tb_course_table_detail_teacher c on b.id = c.course_table_detail_id left JOIN tb_group tg on a.group_id = tg.id " +
                " LEFT JOIN tb_course_category cc ON cc.id = a.course_category_id " +
                " where d.use_state=1 and a.school_year = '" + groupParam.getSchoolYear() + "' and a.term = '" + groupParam.getTerm() +
                "' and tg.group_status = 0";

        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and a.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }

        if (groupParam.getSource() != null) {
            if (groupParam.getSource() == 0) {
                sql += " and a.source = '0' ";
            } else {
                sql += " and a.source != '0' ";
            }
        }
        if (!StringUtils.isEmpty(groupParam.getCourseName())) {
            String courseName = "%" + CharactersReplace.replaceCharacters(groupParam.getCourseName().trim()) + "%";
            sql += " and a.course_name like :courseName";
            paramMap.put("courseName", courseName);
        }
        if (!StringUtils.isEmpty(groupParam.getGroupName())) {
            String groupName = "%" + CharactersReplace.replaceCharacters(groupParam.getGroupName().trim()) + "%";
            sql += " and tg.group_name like :groupName";
            paramMap.put("groupName", groupName);
        }
        if (!StringUtils.isEmpty(groupParam.getCourseNameOrCode())) {
            String courseNameOrCode = "%" + CharactersReplace.replaceCharacters(groupParam.getCourseNameOrCode().trim()) + "%";
            sql += " and ( d.course_code like :courseNameOrCode or d.course_name like :courseNameOrCode )";
            paramMap.put("courseNameOrCode", courseNameOrCode);
        }
        if (!StringUtils.isEmpty(groupParam.getTeacherNameOrCode())) {
            String teacherNameOrCode = "%" + CharactersReplace.replaceCharacters(groupParam.getTeacherNameOrCode().trim()) + "%";
            sql += " and ( c.teacher_name like :teacherNameOrCode or c.teacher_no like :teacherNameOrCode )";
            paramMap.put("teacherNameOrCode", teacherNameOrCode);
        }
        if (!StringUtils.isEmpty(groupParam.getCourseCategoryId())) {
            sql = sql + " and  cc.id like :courseCategoryId ";
            paramMap.put("courseCategoryId", groupParam.getCourseCategoryId());
        }
        if (!StringUtils.isEmpty(groupParam.getCollegeId())) {
            sql = sql + " and  a.college_id like :collegeId ";
            paramMap.put("collegeId", groupParam.getCollegeId());
        }
        if (groupParam.isPublicStatus() != null) {
            if (groupParam.getGroupIds() != null && !groupParam.getGroupIds().isEmpty() && groupParam.isPublicStatus()) {
                sql = sql + " and  tg.id in (:groupIdList)";
                paramMap.put("groupIdList", groupParam.getGroupIds());
            }
            if (groupParam.getGroupIds() != null && !groupParam.getGroupIds().isEmpty() && !groupParam.isPublicStatus()) {
                sql = sql + " and  tg.id not in (:groupIdList)";
                paramMap.put("groupIdList", groupParam.getGroupIds());
            }
        }

        sql += " GROUP BY a.group_id ORDER BY a.create_time desc,a.group_id asc";
        return sql;
    }


    //endregion

    public int getGroupCountBySchoolYearTerm(String schoolYear, int term) {
        String sql = "SELECT count(1) as total" +
                " FROM tb_course_table a  " +
                " JOIN tb_group tg on a.group_id = tg.id " +
                " where a.school_year = '" + schoolYear + "' and a.term = '" + term +
                "' and tg.group_status = 0";
        Map<String, Object> paramMap = new HashMap<>();
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and a.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }
        Query sqlCount = entityManager.createNativeQuery(sql);
        paramMap.forEach(sqlCount::setParameter);
        sqlCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> countMap = (Map<String, Object>) sqlCount.getResultList().get(0);
        return ((BigInteger) countMap.get("total")).intValue();
    }

    //region 根据班级信息Id查询班级详情
    public ResponseEntity<List<GroupSimpleVo>> getGroupSimpleInfos(String groupIds) {
        List<String> groupIdList = Arrays.asList(groupIds.split(","));
        if (CollectionUtils.isEmpty(groupIdList)) {
            return new ResponseEntity(ErrorResult.parameterInvalidError(), HttpStatus.BAD_REQUEST);
        }
        List<Group> groups = this.groupRepository.findByIdIn(groupIdList);
        if (CollectionUtils.isEmpty(groups)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<GroupSimpleVo> groupSimpleVos = this.getGroupSimpleVos(groups);
        if (CollectionUtils.isEmpty(groupSimpleVos)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(groupSimpleVos, HttpStatus.OK);
        }
    }

    private List<GroupSimpleVo> getGroupSimpleVos(List<Group> groups) {
        List<GroupSimpleVo> groupSimpleVos = new ArrayList<>();
        for (Group group : groups) {
            GroupSimpleVo groupSimpleVo = new GroupSimpleVo();
            groupSimpleVo.setGroupId(group.getId());
            groupSimpleVo.setGroupName(group.getGroupName());
            groupSimpleVo.setClassCompositionName(group.getClassCompositionName());
            groupSimpleVo.setClassNickName(group.getClassNickName());
            if (group.getSource() != null) {
                groupSimpleVo.setSource(group.getSource().getIndex());
            }
            groupSimpleVos.add(groupSimpleVo);
            CourseTable courseTable = group.getCourseTableList().get(0);
            if (!ObjectUtils.isEmpty(courseTable)) {
                CourseCategory courseCategory = courseTable.getCourseCategory();
                groupSimpleVo.setCourseCategoryCode(courseCategory.getId() + "");
                groupSimpleVo.setCourseCategoryName(courseCategory.getCourseCategoryName());
            }

        }
        return groupSimpleVos;
    }
    //endregion

    public GroupUserStatisticsResource getGroupUserCountBySchoolYearTerm(String schoolYear, Integer term, String collegeId) {
        Integer studentCount = getStudentGroupCount(schoolYear, term, collegeId);
        Integer teacherCount = getTeacherGroupCount(schoolYear, term, collegeId);
        GroupUserStatisticsResource groupUserStatisticsResource = new GroupUserStatisticsResource();
        groupUserStatisticsResource.setStudentCount(studentCount);
        groupUserStatisticsResource.setTeacherCount(teacherCount);
        return groupUserStatisticsResource;
    }

    private Integer getStudentGroupCount(String schoolYear, Integer term, String collegeId) {
        String sql = " SELECT count( DISTINCT student_id ) AS studentCount " +
                "FROM tb_group_member a INNER JOIN tb_group b ON a.group_id = b.id " +
                "INNER JOIN tb_course_table c ON b.id = c.group_id WHERE ";
        if (!ObjectUtils.isEmpty(collegeId)) {
            sql += "c.college_Id = :collegeId and ";
        }
        sql += "c.school_year = :schoolYear AND c.term = :term" +
                " AND b.group_status = 0 AND a.student_id <> ''" +
                " and a.group_member_status = 0;";
        Map<String, Object> paramMap = new HashMap<>();

        Query sqlCount = entityManager.createNativeQuery(sql);
        if (!ObjectUtils.isEmpty(collegeId)) {
            sqlCount.setParameter("collegeId", collegeId);
        }
        sqlCount.setParameter("schoolYear", schoolYear);
        sqlCount.setParameter("term", term);
        paramMap.forEach(sqlCount::setParameter);
        sqlCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> countMap = (Map<String, Object>) sqlCount.getResultList().get(0);
        return ((BigInteger) countMap.get("studentCount")).intValue();
    }

    private Integer getTeacherGroupCount(String schoolYear, Integer term, String collegeId) {
        String sql = " select COUNT(DISTINCT teacher_id) as teacherCount  from tb_course_table_detail_teacher a " +
                " INNER JOIN tb_course_table_detail d on a.course_table_detail_id = d.id " +
                " INNER JOIN tb_course_table b on d.course_table_id = b.id " +
                " INNER JOIN tb_group c on b.group_id = c.id where ";
        if (!ObjectUtils.isEmpty(collegeId)) {
            sql += " b.college_Id = :collegeId and ";
        }
        sql += " b.school_year = :schoolYear  " +
                " and b.term = :term and c.group_status = 0 and a.teacher_id <>'';";

        Map<String, Object> paramMap = new HashMap<>();

        Query sqlCount = entityManager.createNativeQuery(sql);
        if (!ObjectUtils.isEmpty(collegeId)) {
            sqlCount.setParameter("collegeId", collegeId);
        }
        sqlCount.setParameter("schoolYear", schoolYear);
        sqlCount.setParameter("term", term);
        paramMap.forEach(sqlCount::setParameter);
        sqlCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> countMap = (Map<String, Object>) sqlCount.getResultList().get(0);
        return ((BigInteger) countMap.get("teacherCount")).intValue();
    }

    public List<GroupMemberResourceVo> getGroupMemberList(String schoolYear, String term) {
        List<String> schoolYears = Arrays.asList(schoolYear.split(","));
        List<String> terms = Arrays.asList(term.split(","));
        String schoolYearResult = schoolYears.stream().collect(Collectors.joining("' , '"));
        String termResult = terms.stream().collect(Collectors.joining("' , '"));
        String sql = "SELECT a.group_id as teachingClassId,c.group_name as teachingClassName,a.student_no as studentNo," +
                " a.student_id as studentId,a.student_name as studentName," +
                " a.administrative_class_name as administrativeClassName  " +
                "from tb_group_member a " +
                "INNER JOIN tb_course_table b on a.group_id=b.group_id " +
                "INNER JOIN tb_group c on a.group_id=c.id " +
                "where b.school_year in ('" + schoolYearResult + "') and b.term in ('" + termResult + "') " +
                "and b.source='0' and a.group_member_status='0' and a.source='0'";
        Map<String, Object> paramMap = new HashMap<>();
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and b.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupMemberResourceVo.class));
        List<GroupMemberResourceVo> groupMemberResourceVoList = query.getResultList();
        return groupMemberResourceVoList;
    }

    public MemberNumVo getGroupMemberCount(String schoolYear, String term, String courseId, String groupId) {
        MemberNumVo memberNumVo = new MemberNumVo();
        List<String> schoolYears = Arrays.asList(schoolYear.split(","));
        List<String> terms = Arrays.asList(term.split(","));
        List<String> groupIds = Arrays.asList(groupId.split(","));
        String schoolYearResult = schoolYears.stream().collect(Collectors.joining("' , '"));
        String termResult = terms.stream().collect(Collectors.joining("' , '"));
        String groupIdResult = groupIds.stream().collect(Collectors.joining("' , '"));
        String sql = "SELECT  count(DISTINCT a.student_id) as memberNum" +
                " from tb_group_member a " +
                " INNER JOIN tb_course_table b on a.group_id=b.group_id " +
                " where b.school_year in ('" + schoolYearResult + "') and b.term in ('" + termResult + "') " +
                " and a.group_id in ('" + groupIdResult + "')" +
                " and a.group_member_status='0' " +
                " and b.course_id = '" + courseId + "'";
        Map<String, Object> paramMap = new HashMap<>();
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and c.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        int count = ((BigInteger) resMap.get("memberNum")).intValue();
        memberNumVo.setMemberNum(count);
        return memberNumVo;
    }

    public TermRunningModel getTermRunningNums(List<TermResource> termResources, String courseId) {
        TermRunningModel termRunningModel = new TermRunningModel();
        List<Map<String, String>> groupList = getOpenClassNum(termResources, courseId);

        termRunningModel.setOpenClassNum(String.valueOf(groupList.size()));
        termRunningModel.setServicestudentNum("0");
        termRunningModel.serviceTeacherNum("0");

        if (groupList.size() > 0) {
            String serviceStudentNum = getServiceStudentNum(groupList);
            String serviceTeacherNum = getServiceTeacherNum(groupList);
            termRunningModel.setOpenClassNum(String.valueOf(groupList.size()));
            termRunningModel.setServicestudentNum(serviceStudentNum);
            termRunningModel.serviceTeacherNum(serviceTeacherNum);
        }
        return termRunningModel;
    }

    public List<Map<String, String>> getOpenClassNum(List<TermResource> termResources, String courseId) {
        String schoolYearResult = termResources.stream().map(termResource ->
                "'" + termResource.getSchoolYear() + "'," + termResource.getTerm()).collect(Collectors.joining("),("));
        String sql = "select b.id as id,b.group_no as groupNo,b.group_name as  groupName from tb_course_table  a  " +
                " INNER JOIN tb_group  b on a.group_id = b.id " +
                "where (a.school_year, a.term) in ((" + schoolYearResult + ")) and a.course_id = '" + courseId + "' " +
                "and b.group_status = 0 ";
        Map<String, Object> paramMap = new HashMap<>();
        if (!studentType.equals("-1")) {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql += " and a.student_type = :studentType ";
            paramMap.put("studentType", studentTypeQuery);
        }

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return (List<Map<String, String>>) query.getResultList();
    }

    public String getServiceStudentNum(List<Map<String, String>> groupList) {

        String groupIdResult = groupList.stream().map(groupMap -> groupMap.get("id")).collect(Collectors.joining("' , '"));
        String sql = "SELECT  count(DISTINCT a.student_id) as memberNum from tb_group_member a  " +
                "where a.group_id in ('" + groupIdResult + "') and a.group_member_status='0' ";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        int count = ((BigInteger) resMap.get("memberNum")).intValue();
        return String.valueOf(count);
    }

    public String getServiceTeacherNum(List<Map<String, String>> groupList) {

        List<String> groupIdResult = groupList.stream().map(groupMap -> groupMap.get("id")).collect(Collectors.toList());
        String sql = "";
        Map<String, Object> paramMap = new HashMap<>();

        if (studentType.equals("-1")) {
            sql = "select count(DISTINCT a.teacher_id) as memberNum from ( " +
                    "SELECT * FROM tb_course_table_detail_teacher WHERE course_table_detail_id in " +
                    "(SELECT id FROM tb_course_table_detail where course_table_id in( " +
                    "SELECT id from tb_course_table WHERE  " +
                    "group_id IN (:groupIdResult)))  GROUP BY teacher_id " +
                    ") a ;";
            paramMap.put("groupIdResult", groupIdResult);
        } else {
            Integer studentTypeQuery = Integer.parseInt(studentType);
            sql = "select count(DISTINCT a.teacher_id) as memberNum from ( " +
                    "SELECT * FROM tb_course_table_detail_teacher WHERE course_table_detail_id in " +
                    "(SELECT id FROM tb_course_table_detail where course_table_id in( " +
                    "SELECT id from tb_course_table WHERE  student_type = :studentType and " +
                    " group_id IN (:groupIdResult)))  GROUP BY teacher_id " +
                    ") a ;";
            paramMap.put("studentType", studentTypeQuery);
            paramMap.put("groupIdResult", groupIdResult);
        }

        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> resMap = (Map<String, Object>) query.getResultList().get(0);
        int count = ((BigInteger) resMap.get("memberNum")).intValue();
        return String.valueOf(count);
    }


    public List<Group> getGroupsByIds(List<String> ids) {
        return groupRepository.findByIdIn(ids);
    }

    @Transactional
    public void deleteGroupsByIds(List<Group> groups, String operatorId, String operatorName) {
        Date now = new Date();
        groups.forEach(group -> {
            List<GroupMember> groupMembers = new ArrayList<>();
            group.setGroupStatus(GroupStatus.REMOVE);
            group.setModifierId(operatorId);
            group.setModifierName(operatorName);
            group.setModifyTime(now);

            group.getGroupMemberList().forEach(groupMember -> {
                groupMember.setGroupMemberStatus(GroupMemberStatus.REMOVE);
                groupMember.setModifierId(operatorId);
                groupMember.setModifierName(operatorName);
                groupMember.setModifyTime(now);
                groupMembers.add(groupMember);
            });

            groupMemberRepository.saveAll(groupMembers);
            groupRepository.save(group);

            if (dataVisualEnable) {
                eventService.sendDeleteGroupEvent(group.getId());
            }
        });
    }

    public ImportClassVo multipartFile(MultipartFile importFile, String operatorId, String operatorName) throws Exception {
        CacheUtil.put(Constant.OPERATOR_ID, operatorId);
        CacheUtil.put(Constant.OPERATOR_NAME, operatorName);

        // 查询数据库中所有学期
        List<com.lztech.site.resource.term.TermResource> termResourceList = termService.getTermList().getBody();
        if (ObjectUtil.isNotEmpty(termResourceList)) {
            List<String> termResourceIdList = Objects.requireNonNull(termResourceList)
                    .stream()
                    .map(com.lztech.site.resource.term.TermResource::getId)
                    .collect(Collectors.toList());
            CacheUtil.put(Constant.TERM_RESOURCE_ID_LIST, termResourceIdList);
        }
        // 查询数据库中所有的班级编号
        List groupNoList = getAllGroupNo();
        if (CollectionUtils.isNotEmpty(groupNoList)) {
            CacheUtil.put(Constant.GROUP_NO_LIST, groupNoList);
        }

        // 查询数据库中所有的课程编号
        Map<String, String> allCourseCodeAllCourseName = getAllCourseCodeAllCourseName();
        if (MapUtil.isNotEmpty(allCourseCodeAllCourseName)) {
            CacheUtil.put(Constant.COURSE_CODE_AND_COURSE_NAME_MAP, allCourseCodeAllCourseName);
        }

        // 查询数据库中所有的学院编号
        Map<String, String> allCollegeCodeAndCollegeName = getAllCollegeCodeAndCollegeName();
        if (MapUtil.isNotEmpty(allCollegeCodeAndCollegeName)) {
            CacheUtil.put(Constant.COLLEGE_CODE_AND_COLLEGE_NAME_MAP, allCollegeCodeAndCollegeName);
        }

        //获取所有老师信息并过滤启用的
        List<TeacherVo> authorityApiServiceAllTeachers = authorityApiService.getAllTeachers();
        Map<String, TeacherVo> teacherVoMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(authorityApiServiceAllTeachers)) {
            authorityApiServiceAllTeachers.forEach(teacherVo -> teacherVoMap.put(teacherVo.getTeacherNo(), teacherVo));
            List<String> allTeacherNo = authorityApiServiceAllTeachers.stream().map(TeacherVo::getTeacherNo).collect(Collectors.toList());
            CacheUtil.put(Constant.ALL_TEACHER_NO, allTeacherNo);
            CacheUtil.put(Constant.ALL_TEACHER_MODEL, teacherVoMap);
        }

        EasyExcel
                .read(importFile.getInputStream(),
                        ImportClassExcelObject.class,
                        new ImportClassListener(courseRepository, courseCategoryRepository, groupRepository,
                                collegeRepository, courseTableDetailService))
                .sheet().doRead();


        return (ImportClassVo) CacheUtil.get(Constant.IMPORT_CLASS_VO);
    }

    public List getAllGroupNo() {
        String sql = "select group_no from tb_group where group_status = 0";
        Query sqlCount = entityManager.createNativeQuery(sql);
        sqlCount.unwrap(NativeQueryImpl.class);
        return sqlCount.getResultList();
    }

    private Map<String, String> getAllCourseCodeAllCourseName() {
        HashMap<String, String> allCourseCodeAllCourseNameMap = new HashMap<>();
        allCourseCodeAllCourseNameMap.put("1", "123");
        String sql = "select course_code,course_name from tb_course";
        Query sqlCount = entityManager.createNativeQuery(sql);
        sqlCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map<String, String>> resultList = sqlCount.getResultList();
        resultList.forEach(item -> {
            allCourseCodeAllCourseNameMap.put(item.get("course_code"), item.get("course_name"));

        });
        return allCourseCodeAllCourseNameMap;
    }

    private Map<String, String> getAllCollegeCodeAndCollegeName() {
        HashMap<String, String> collegeCodeAndCollegeNameMap = new HashMap<>();


        String sql = "select college_code,college_name from tb_college";
        Query sqlCount = entityManager.createNativeQuery(sql);
        sqlCount.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map<String, String>> resultList = sqlCount.getResultList();
        resultList.forEach(item -> {
            collegeCodeAndCollegeNameMap.put(item.get("college_code"), item.get("college_name"));

        });
        return collegeCodeAndCollegeNameMap;
    }

    public List<GroupResourceVo> getGroupListByStudentId(String studentId, String schoolYear, Integer term) {
        String sql = "SELECT a.group_no as groupNo,a.id as groupId,a.group_name as groupName " +
                "FROM tb_group a " +
                "INNER JOIN tb_group_member b on a.id=b.group_id " +
                "INNER JOIN tb_course_table c on a.id=c.group_id " +
                "where  a.group_status=0 and b.group_member_status=0 And b.student_id=:studentId ";
        if (!StringUtils.isEmpty(schoolYear)) {
            sql += "and c.school_year=:schoolYear ";
        }
        if (!ObjectUtils.isEmpty(term)) {
            sql += "and c.term=:term ";
        }
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("studentId", studentId);
        if (!StringUtils.isEmpty(schoolYear)) {
            query.setParameter("schoolYear", schoolYear);
        }
        if (!ObjectUtils.isEmpty(term)) {
            query.setParameter("term", term);
        }
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupResourceVo.class));
        List<GroupResourceVo> groupResourceVoList = query.getResultList();
        return groupResourceVoList;
    }

    public List<GroupBaseInfo> getCollegeGroupList(String schoolYear, Integer term, String collegeIds) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT distinct a.group_id AS groupId,b.group_no AS groupNo,b.group_name AS groupName, " +
                "a.college_id AS collegeId,a.college_name AS collegeName " +
                "FROM tb_course_table a INNER JOIN tb_group b ON a.group_id = b.id " +
                "WHERE b.group_status = 0 AND a.school_year =:schoolYear AND a.term =:term " +
                "and a.college_id in (:collegeIdList)";
        paramMap.put("schoolYear", schoolYear);
        paramMap.put("term", term);
        List<String> collegeIdList = Arrays.asList(collegeIds.split(","));
        paramMap.put("collegeIdList", collegeIdList);
        Query queryData = entityManager.createNativeQuery(sql);
        paramMap.forEach(queryData::setParameter);
        queryData.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GroupBaseInfo.class));
        return queryData.getResultList();

    }
}
