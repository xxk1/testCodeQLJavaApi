package com.lztech.site.service.teachingcenterstatistics;

import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.classroom.ClassRoomService;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.classroom.ClassroomBaseInfoVo;
import com.lztech.site.viewmodel.teachingcenterstatistics.TeachingCenterJobTitleStatistics;
import com.lztech.site.viewmodel.userinfo.Users;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
public class TeachingCenterStatisticsService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private AuthorityApiService authorityApiService;


    public List<TeachingCenterJobTitleStatistics> getCourseJobTitleTeacherStatistics(String teachingCenterId,
                                                                               String schoolYear,
                                                                               Integer term) {
        List<ClassroomBaseInfoVo> classroomBaseInfoVoList = classRoomService.getTeachingCenterClassroomList(teachingCenterId);
        if (CollectionUtils.isEmpty(classroomBaseInfoVoList)) {
            return new ArrayList<>();
        }
        List<String> classRoomIdList = classroomBaseInfoVoList.stream().map(ClassroomBaseInfoVo::getRoomId).collect(Collectors.toList());
        List<UsersInfoResource> usersInfoResourceList = authorityApiService.getUsersInfo(getCourseTeacherInfo(classRoomIdList, schoolYear, term));
        Map<String, List<UsersInfoResource>> map = usersInfoResourceList
                .stream()
                .filter(u -> StringUtils.isNotBlank(u.getJobTitle()))
                .collect(Collectors.groupingBy(UsersInfoResource::getJobTitle));

        List<TeachingCenterJobTitleStatistics> jobTitleStatisticsList = new ArrayList<>();
        map.forEach((title, list) -> {
            TeachingCenterJobTitleStatistics jobTitleStatistics = new TeachingCenterJobTitleStatistics();
            jobTitleStatistics.setJobTitleName(title);
            jobTitleStatistics.setTeacherNum(CollectionUtils.isNotEmpty(list) ? list.size() : 0);
            jobTitleStatisticsList.add(jobTitleStatistics);
        });

        return jobTitleStatisticsList
                .stream()
                .sorted(Comparator.comparing(TeachingCenterJobTitleStatistics::getTeacherNum).reversed())
                .collect(Collectors.toList());
    }

    private List<Users> getCourseTeacherInfo(List<String> roomIdList,
                                             String schoolYear,
                                             Integer term) {

        String sql = "select ctdt.teacher_id as userId " +
                "from tb_course_table ct \n" +
                "INNER JOIN tb_course_table_detail ctd on ct.id = ctd.course_table_id\n" +
                "INNER JOIN tb_course_table_detail_room_user ctdru on ctd.id = ctdru.course_table_detail_id\n" +
                "INNER JOIN tb_course_table_detail_teacher ctdt on ctd.id = ctdt.course_table_detail_id\n" +
                "where 1=1 ";

        if (CollectionUtils.isNotEmpty(roomIdList)) {
            String roomIds = roomIdList.stream().map(id -> "'" + id + "'").collect(joining(","));
            sql += " and ctdru.room_id in ( " + roomIds + ") ";
        }
        if (StringUtils.isNotBlank(schoolYear)) {
            sql += " and ct.school_year ='" + schoolYear + "' ";
        }
        if (Objects.nonNull(term)) {
            sql += " and ct.term=" + term;
        }

        sql += " GROUP BY ctdt.teacher_id,ctdt.teacher_name,ctdt.teacher_no";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(Users.class));
        return query.getResultList();
    }
}
