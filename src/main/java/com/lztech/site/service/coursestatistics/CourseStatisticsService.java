package com.lztech.site.service.coursestatistics;

import com.lztech.domain.model.bigdata.BigDataTemp;
import com.lztech.domain.model.course.*;
import com.lztech.domain.model.course.enums.*;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.coursetabledetailteacher.CourseTableDetailTeacher;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.domain.model.groupmember.GroupMember;
import com.lztech.domain.model.groupmember.enums.GroupMemberStatus;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.persistence.repositories.bigdata.BigDataTempRepository;
import com.lztech.persistence.repositories.course.*;
import com.lztech.persistence.repositories.courseknowledgestructure.CourseKnowledgeStructureRepository;
import com.lztech.persistence.repositories.coursetable.CourseTableRepository;
import com.lztech.persistence.repositories.coursetabledetailteacher.CourseTableDetailTeacherRepository;
import com.lztech.persistence.repositories.courseversion.CourseVersionRepository;
import com.lztech.persistence.repositories.groupmember.GroupMemberRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.authorityapi.AuthorityApiService;
import com.lztech.site.service.courseconstruction.CourseKnowledgeStructureService;
import com.lztech.site.service.courseconstruction.CourseTeachingTeamService;
import com.lztech.site.service.courseknowledgegraph.CourseKnowledgeGraphService;
import com.lztech.site.viewmodel.authorityapi.UsersInfoResource;
import com.lztech.site.viewmodel.coursemanagement.CourseKnowledgePointResource;
import com.lztech.site.viewmodel.coursestatistics.*;
import com.lztech.site.viewmodel.coursestatistics.sql.CourseResourceStatisticsVo;
import com.lztech.site.viewmodel.coursestatistics.sql.CourseStatisticsTypeVo;
import com.lztech.site.viewmodel.coursestatistics.sql.ResourceDetailVo;
import com.lztech.site.viewmodel.userinfo.Users;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CourseStatisticsService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private BigDataTempRepository bigDataTempRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseTeachingTeamRepository courseTeachingTeamRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseCompletionRepository courseCompletionRepository;
    @Autowired
    private CourseTableRepository courseTableRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private CourseTableDetailTeacherRepository courseTableDetailTeacherRepository;
    @Autowired
    private CourseVersionRepository courseVersionRepository;
    @Autowired
    private CourseStructureRepository courseStructureRepository;
    @Autowired
    private CourseTeachingTeamService teachingTeamService;
    @Autowired
    private CourseKnowledgeStructureRepository courseKnowledgeStructureRepository;
    @Autowired
    private CourseKnowledgeStructureService courseKnowledgeStructureService;
    @Autowired
    private AuthorityApiService authorityApiService;
    @Autowired
    private CourseKnowledgeGraphService courseKnowledgeGraphService;

    public CourseStatisticsResource getCourseStatistics(Integer dimension,
                                                        String schoolYear,
                                                        String term) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = getQuerySql(dimension, schoolYear, term, paramMap);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseStatisticsTypeVo.class));
        List resultList = query.getResultList();
        CourseStatisticsResource courseStatisticsResource = new CourseStatisticsResource();
        courseStatisticsResource.setTotalNum(0);
        courseStatisticsResource.setResourceList(new ArrayList<>());
        if (CollectionUtils.isNotEmpty(resultList)) {
            List<CourseStatisticsTypeVo> voList = (List<CourseStatisticsTypeVo>) resultList;
            int totalNum = 0;
            List<CourseStatisticsDetailResource> courseStatisticsDetailResourceList = new ArrayList<>();
            for (CourseStatisticsTypeVo courseStatisticsTypeVo : voList) {
                CourseStatisticsDetailResource courseStatisticsDetailResource = new CourseStatisticsDetailResource();
                courseStatisticsDetailResource.setTypeName(courseStatisticsTypeVo.getTypeName());
                courseStatisticsDetailResource.setTypeNum(courseStatisticsTypeVo.getTypeNum().intValue());
                totalNum += courseStatisticsTypeVo.getTypeNum().intValue();
                courseStatisticsDetailResourceList.add(courseStatisticsDetailResource);
            }
            courseStatisticsResource.setResourceList(courseStatisticsDetailResourceList);
            courseStatisticsResource.setTotalNum(totalNum);
        }
        return courseStatisticsResource;
    }

    private String getQuerySql(Integer dimension,
                               String schoolYear, String term,
                               Map<String, Object> paramMap) {

        String baseSql = "";
        if (dimension == 0) {
            baseSql += "select tcc.course_category_name typeName," +
                    " count(distinct ct.course_id) typeNum " +
                    " from tb_course_table ct inner join tb_course_category tcc on " +
                    "ct.course_category_id = tcc.id where 1=1";

        } else {
            baseSql += "select (case student_type when 0 then '本科生课程' when 1 then '研究生课程' else '其他' end) typeName," +
                    " count(distinct course_id)  typeNum " +
                    " from tb_course_table ct where 1=1";
        }

        if (StringUtils.isNotBlank(schoolYear)) {
            baseSql += " and ct.school_year= :schoolYear ";
            paramMap.put("schoolYear", schoolYear);
        }
        if (StringUtils.isNotBlank(term)) {
            baseSql += " and ct.term= :term ";
            paramMap.put("term", term);
        }
        if (dimension == 0) {
            baseSql += " group by tcc.id;";
        } else {
            baseSql += " group by student_type";
        }
        return baseSql;
    }

    public CourseResourceStatisticsResource getCourseResourceStatistics() {

        CourseResourceStatisticsResource courseResourceStatisticsResource = new CourseResourceStatisticsResource();
        courseResourceStatisticsResource.setTotalNum(0);
        courseResourceStatisticsResource.setTeachingContentNum(0);
        courseResourceStatisticsResource.setTeachingActivityNum(0);

        String sql = "select\n" +
                "       count(id) as totalNum,\n" +
                "       count(case when resource_type in (0, 1, 3) then 1 end) teachingContentNum,\n" +
                "       count(case when resource_type in (2,4) then 1 end) teachingActivityNum\n" +
                "from tb_course_resource cr where cr.resource_status=0\n";

        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(CourseResourceStatisticsVo.class));
        List queryResultList = query.getResultList();
        if (CollectionUtils.isNotEmpty(queryResultList)) {
            CourseResourceStatisticsVo courseResourceStatisticsVo = (CourseResourceStatisticsVo) queryResultList.get(0);
            courseResourceStatisticsResource.setTotalNum(courseResourceStatisticsVo.getTotalNum().intValue());
            courseResourceStatisticsResource.setTeachingContentNum(courseResourceStatisticsVo.getTeachingContentNum().intValue());
            courseResourceStatisticsResource.setTeachingActivityNum(courseResourceStatisticsVo.getTeachingActivityNum().intValue());
        }
        return courseResourceStatisticsResource;
    }

    public CourseResourceDetailStatisticsVo getCourseResourceDetailStatistics() {
        CourseResourceDetailStatisticsVo statisticsVo = new CourseResourceDetailStatisticsVo();
        statisticsVo.setTotalNum(0);
        statisticsVo.setTypeList(new ArrayList<>());

        String sql = "select (case resource_type\n" +
                "            when 0 then '课件'\n" +
                "            when 1 then '视频'\n" +
                "            when 2 then '活动'\n" +
                "            when 3 then '图片' end) typeName,\n" +
                "       count(1) typeNum " +
                " from (select IF(resource_type = 4, 2, resource_type) resource_type\n" +
                "      from tb_course_resource\n" +
                "      where resource_status = 0) a\n" +
                "group by resource_type";

        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ResourceDetailVo.class));
        List queryResultList = query.getResultList();

        if (CollectionUtils.isNotEmpty(queryResultList)) {
            List<ResourceTypeDetailStatisticsVo> typeList = new ArrayList<>();
            List<ResourceDetailVo> detailVoList = (List<ResourceDetailVo>) queryResultList;
            int totalNum = 0;
            for (ResourceDetailVo resourceDetailVo : detailVoList) {
                ResourceTypeDetailStatisticsVo resourceTypeDetailStatisticsVo = new ResourceTypeDetailStatisticsVo();
                resourceTypeDetailStatisticsVo.setTypeName(resourceDetailVo.getTypeName());
                resourceTypeDetailStatisticsVo.setTypeNum(resourceDetailVo.getTypeNum().intValue());
                totalNum += resourceDetailVo.getTypeNum().intValue();
                typeList.add(resourceTypeDetailStatisticsVo);
            }
            statisticsVo.setTotalNum(totalNum);
            statisticsVo.setTypeList(typeList);
        }

        return statisticsVo;
    }

    public CourseResourceUseStatisticsResource getCourseResourceUseStatistics() {
        CourseResourceUseStatisticsResource courseResourceUseStatisticsResource = new CourseResourceUseStatisticsResource();
        courseResourceUseStatisticsResource.setResourceBrowsingNum(0);
        courseResourceUseStatisticsResource.setResourceDownloadNum(0);
        int resourceReferencesNum = courseResourceRepository.countResourceReferences();
        courseResourceUseStatisticsResource.setResourceQuoteNum(resourceReferencesNum);
        BigDataTemp bigDataTemp = bigDataTempRepository.findById("1").orElse(null);
        if (Objects.nonNull(bigDataTemp)) {
            courseResourceUseStatisticsResource.setResourceBrowsingNum(bigDataTemp.getResourceBrowsingNum());
            courseResourceUseStatisticsResource.setResourceDownloadNum(bigDataTemp.getResourceDownloadNum());
        }

        return courseResourceUseStatisticsResource;

    }

    public CourseBuildStatisticsResource getCourseBuildStatistics() {
        CourseBuildStatisticsResource courseBuildStatisticsResource = new CourseBuildStatisticsResource();
        courseBuildStatisticsResource.setCompleteRate(BigDecimal.valueOf(0).setScale(Constant.TWO, RoundingMode.DOWN));
        courseBuildStatisticsResource.setRateGrowth(BigDecimal.ZERO);
        courseBuildStatisticsResource.setBuildCourseNum(0);
        courseBuildStatisticsResource.setCourseTeamTeacherNum(0);

        List<CourseCompletion> courseCompletionList = courseCompletionRepository.findAll();
        if (CollectionUtils.isEmpty(courseCompletionList)) {
            return courseBuildStatisticsResource;
        }
        BigDataTemp bigDataTemp = bigDataTempRepository.findById("1").orElse(null);
        int buildCourseNum =
                (int) courseCompletionList.stream().filter(CourseCompletion::isCompleted).map(c -> c.getCourse().getId()).distinct().count();
        courseBuildStatisticsResource.setBuildCourseNum(buildCourseNum);

        double completeRate = (courseCompletionList
                .stream()
                .filter(CourseCompletion::isCompleted)
                .mapToDouble(c -> c.getCourseContentEnum()
                        .getCompletionPercent()).sum()) / (double) buildCourseNum;

        courseBuildStatisticsResource.setCompleteRate(BigDecimal.valueOf(completeRate).setScale(Constant.TWO, RoundingMode.DOWN));
        if (Objects.isNull(bigDataTemp)) {
            courseBuildStatisticsResource.setRateGrowth(BigDecimal.valueOf(completeRate).setScale(Constant.TWO, RoundingMode.CEILING));

        } else {
            courseBuildStatisticsResource.setRateGrowth(BigDecimal.valueOf(completeRate - bigDataTemp.getLastCompletionRate()).setScale(Constant.TWO,
                    RoundingMode.CEILING));
        }
        int courseTeamNum = courseRepository.countByCourseLeaderIdIsNotNull();
        courseBuildStatisticsResource.setCourseTeamTeacherNum(courseTeamNum);

        return courseBuildStatisticsResource;
    }

    public CourseStatisticsPeopleNumberVo getCoursePeopleNumber(String courseId, String schoolYear, String term) {
        Integer integerTern = Integer.parseInt(term);
        CourseStatisticsPeopleNumberVo courseStatisticsPeopleNumberVo = new CourseStatisticsPeopleNumberVo();
        List<CourseTable> courseTables =
                courseTableRepository.findByCourseIdAndTermAndSchoolYear(courseId, integerTern, schoolYear);
        List<CourseTable> courseTableList = courseTables
                .stream()
                .filter(c -> c.getGroup().getGroupStatus() == GroupStatus.NORMAL)
                .collect(Collectors.toList());
        courseStatisticsPeopleNumberVo.setClassNumebr(courseTableList.size());
        List<Group> groupList = courseTableList.stream().map(CourseTable::getGroup).collect(Collectors.toList());

        List<GroupMember> groupMembers = groupMemberRepository.findByGroupIsIn(groupList);
        ArrayList<GroupMember> memberArrayList = groupMembers.stream()
                .filter(groupMember -> groupMember.getGroupMemberStatus() == GroupMemberStatus.NORMAL)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(GroupMember::getStudentId))), ArrayList::new));
        courseStatisticsPeopleNumberVo.setStudentNumebr(memberArrayList.size());
        List<CourseTableDetail> courseTableDetailList = new ArrayList();
        courseTableList.forEach(courseTable -> {
            courseTable.getCourseTableDetailList().forEach(courseTableDetail -> {
                courseTableDetailList.add(courseTableDetail);
            });
        });
        List<CourseTableDetailTeacher> teacherList =
                courseTableDetailTeacherRepository.findByCourseTableDetailIsIn(courseTableDetailList);
        ArrayList<CourseTableDetailTeacher> teacherArrayList = teacherList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(CourseTableDetailTeacher::getTeacherId))), ArrayList::new));
        courseStatisticsPeopleNumberVo.setTeacherNumber(teacherArrayList.size());
        return courseStatisticsPeopleNumberVo;
    }

    public List<CourseStatisticsVersionVo> getCourseStatisticByCourseId(String courseId) {
        List<CourseStatisticsVersionVo> courseStatisticsVersionVos = new ArrayList<>();
        List<CourseVersion> courseVersionList = courseVersionRepository.findByCourseIdAndCourseVersionStatusIsNot(
                courseId, CourseVersionStatus.DELETE);
        if (CollectionUtils.isNotEmpty(courseVersionList)) {
            courseVersionList = courseVersionList.stream().sorted(Comparator.comparing(CourseVersion::getVersionSerialNumber))
                    .collect(Collectors.toList());
            for (CourseVersion courseVersion : courseVersionList) {
                CourseStatisticsVersionVo courseStatisticsVersionVo = new CourseStatisticsVersionVo();
                courseStatisticsVersionVo.setVersionNum("版本" + courseVersion.getVersionSerialNumber());
                List<CourseTeachingTeam> courseTeachingTeamList = courseTeachingTeamRepository.findByCourseIdAndCourseVersionId(
                        courseId, courseVersion.getId());
                Map<String, List<CourseTeachingTeam>> courseTeachingTeamMap = courseTeachingTeamList.stream()
                        .filter(item -> StringUtils.isNotEmpty(item.getTeacherId()))
                        .collect(Collectors.groupingBy(CourseTeachingTeam::getTeacherId));
                courseStatisticsVersionVo.setTeamMemberNum(courseTeachingTeamMap.size());
                List<CourseStructure> courseStructures = courseStructureRepository.findByCourseIdAndCourseVersionIdAndStructureStatus
                        (courseId, courseVersion.getId(), StructureStatus.NORMAL);
                List<CourseResource> courseResources = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(courseStructures)) {
                    for (CourseStructure courseStructure : courseStructures) {
                        if (CollectionUtils.isNotEmpty(courseStructure.getCourseResources())) {
                            courseStructure.getCourseResources().stream().filter(item -> item.getIsPublic()
                                            && item.getResourceStatus().equals(ResourceStatus.NORMAL))
                                    .forEach(item -> {
                                        courseResources.add(item);
                                    });
                        }
                    }
                }
                List<CourseResource> courseResourceListActivity = courseResources.stream().filter(item ->
                        item.getResourceType().equals(ResourceType.CLASS_TEST) ||
                                item.getResourceType().equals(ResourceType.THEME)).collect(Collectors.toList());
                List<CourseResource> courseResourceList = courseResources.stream().filter(item ->
                        item.getResourceType().equals(ResourceType.IMAGE) ||
                                item.getResourceType().equals(ResourceType.MICRO_VIDEO) || item.getResourceType()
                                .equals(ResourceType.TEACHING_COURSE_WARE)).collect(Collectors.toList());
                courseStatisticsVersionVo.setActivityNum(BigDecimal.valueOf(courseResourceListActivity.size()));
                courseStatisticsVersionVo.setResourceNum(BigDecimal.valueOf(courseResourceList.size()));
                courseStatisticsVersionVos.add(courseStatisticsVersionVo);
            }
        }
        return courseStatisticsVersionVos;
    }

    public List<CourseStatisticsTeachResourceVo> getCourseStatisticResourceByCourseId(String courseId) {
        List<CourseVersion> courseVersions =
                courseVersionRepository.findByCourseIdAndVersionStatus(courseId, CourseVersionStatus.IN_USE.getValue());
        CourseVersion courseVersion;
        if (CollectionUtils.isNotEmpty(courseVersions)) {
            courseVersion = courseVersions.get(0);
            List<CourseStatisticsTeachResourceVo> courseStatisticsTeachResourceVos = new ArrayList<>();
            List<CourseStructure> courseStructureList =
                    courseStructureRepository.findByCourseIdAndCourseVersionId(courseId, courseVersion.getId());
            List<CourseResource> courseResourceList = new ArrayList<>();
            List<CourseResource> finalCourseResourceList = courseResourceList;
            courseStructureList.stream().filter(item -> CollectionUtils.isNotEmpty(item.getCourseResources()))
                    .forEach(item -> {
                        finalCourseResourceList.addAll(item.getCourseResources());
                    });
            courseResourceList = finalCourseResourceList;
            courseResourceList = courseResourceList.stream().filter(item -> item.getIsPublic()
                    && item.getResourceStatus().equals(ResourceStatus.NORMAL)).collect(Collectors.toList());
            Map<String, List<CourseResource>> courseResourceMap = courseResourceList.stream()
                    .collect(Collectors.groupingBy(CourseResource::getCreatorId));
            List<CourseStatisticsTeachResourceVo> finalCourseStatisticsTeachResourceVos = courseStatisticsTeachResourceVos;
            courseResourceMap.forEach((teacherId, courseResources) -> {
                CourseStatisticsTeachResourceVo courseStatisticsTeachResourceVo = new CourseStatisticsTeachResourceVo();
                List<CourseTeachingTeam> courseTeachingTeams = courseTeachingTeamRepository
                        .findByCourseIdAndTeacherId(courseId, teacherId);
                List<UsersInfoResource> usersInfos = teachingTeamService.getCourseTeachingTeamTeacherInfo(courseTeachingTeams);
                if (CollectionUtils.isNotEmpty(usersInfos)) {
                    courseStatisticsTeachResourceVo.setAvatarOuterUrl(usersInfos.get(0).getAvatarOuterUrl());
                    courseStatisticsTeachResourceVo.setAvatarInnerUrl(usersInfos.get(0).getAvatarInnerUrl());
                    courseStatisticsTeachResourceVo.setAvatarPath(usersInfos.get(0).getAvatarPath());
                    courseStatisticsTeachResourceVo.setCollegeId(usersInfos.get(0).getCollegeId());
                    courseStatisticsTeachResourceVo.setSchoolName(usersInfos.get(0).getCollegeName());
                    courseStatisticsTeachResourceVo.setJobTitle(courseTeachingTeams.get(0).getJobTitle());
                    courseStatisticsTeachResourceVo.setTeacherId(courseTeachingTeams.get(0).getTeacherNo());
                    courseStatisticsTeachResourceVo.setTeacherName(courseTeachingTeams.get(0).getTeacherName());
                    courseStatisticsTeachResourceVo.setResourceNum(String.valueOf(courseResources.stream()
                            .filter(item -> item.getIsPublic()).collect(Collectors.toList()).size()));
                    int references = courseResources.stream().filter(item -> item.getResourceReferences() != null)
                            .mapToInt(CourseResource::getResourceReferences).sum();
                    courseStatisticsTeachResourceVo.setUseNum(String.valueOf(references));
                    finalCourseStatisticsTeachResourceVos.add(courseStatisticsTeachResourceVo);
                } else {
                    List<Users> users = new ArrayList<>();
                    Users usersInfo = new Users();
                    usersInfo.setUserId(teacherId);
                    users.add(usersInfo);
                    List<UsersInfoResource> usersInfoResourceList = authorityApiService.getUsersInfo(users);
                    if (CollectionUtils.isNotEmpty(usersInfoResourceList)) {
                        courseStatisticsTeachResourceVo.setAvatarOuterUrl(usersInfoResourceList.get(0).getAvatarOuterUrl());
                        courseStatisticsTeachResourceVo.setAvatarInnerUrl(usersInfoResourceList.get(0).getAvatarInnerUrl());
                        courseStatisticsTeachResourceVo.setAvatarPath(usersInfoResourceList.get(0).getAvatarPath());
                        courseStatisticsTeachResourceVo.setCollegeId(usersInfoResourceList.get(0).getCollegeId());
                        courseStatisticsTeachResourceVo.setSchoolName(usersInfoResourceList.get(0).getCollegeName());
                        courseStatisticsTeachResourceVo.setJobTitle(usersInfoResourceList.get(0).getJobTitle());
                        courseStatisticsTeachResourceVo.setTeacherId(usersInfoResourceList.get(0).getUserNo());
                        courseStatisticsTeachResourceVo.setTeacherName(usersInfoResourceList.get(0).getUserRealName());
                        courseStatisticsTeachResourceVo.setResourceNum(String.valueOf(courseResources.stream()
                                .filter(item -> item.getIsPublic()).collect(Collectors.toList()).size()));
                        int references = courseResources.stream().filter(item -> item.getResourceReferences() != null)
                                .mapToInt(CourseResource::getResourceReferences).sum();
                        courseStatisticsTeachResourceVo.setUseNum(String.valueOf(references));
                        finalCourseStatisticsTeachResourceVos.add(courseStatisticsTeachResourceVo);
                    }
                }
            });
            courseStatisticsTeachResourceVos = finalCourseStatisticsTeachResourceVos;
            final int top = 3;
            courseStatisticsTeachResourceVos = courseStatisticsTeachResourceVos.stream()
                    .sorted(Comparator.comparing(CourseStatisticsTeachResourceVo::getTeacherId))
                    .sorted((a, b) -> Integer.valueOf(b.getUseNum()).compareTo(Integer.valueOf(a.getUseNum())))
                    .sorted((a, b) -> Integer.valueOf(b.getResourceNum()).compareTo(Integer.valueOf(a.getResourceNum())))
                    .limit(top)
                    .collect(Collectors.toList());
            return courseStatisticsTeachResourceVos;
        } else {
            return new ArrayList<>();
        }

    }

    public CourseStatisticsResourceVo getResourceStatisticNum(String courseId, String versionId) {
        CourseStatisticsResourceVo courseStatisticsResourceVo = new CourseStatisticsResourceVo();
        List<CourseStructure> courseStructureList = courseStructureRepository
                .findByCourseIdAndCourseVersionIdAndStructureStatus(courseId, versionId, StructureStatus.NORMAL);
        List<CourseResource> courseResourceList = new ArrayList<>();
        List<CourseResource> finalCourseResourceList = courseResourceList;
        for (CourseStructure courseStructure : courseStructureList) {
            if (CollectionUtils.isNotEmpty(courseStructure.getCourseResources()) &&
                    courseStructure.getStructureStatus().equals(StructureStatus.NORMAL)) {
                finalCourseResourceList.addAll(courseStructure.getCourseResources());
            }
        }
        courseResourceList = finalCourseResourceList;
        finalCourseResourceList = finalCourseResourceList.stream().filter(item -> item.getIsPublic())
                .sorted(Comparator.comparing(CourseResource::getCreateTime).reversed())
                .collect(Collectors.toList());
        courseResourceList = courseResourceList.stream().filter(item -> item.getIsPublic()
                        && item.getResourceStatus().equals(ResourceStatus.NORMAL))
                .sorted(Comparator.comparing(CourseResource::getCreateTime).reversed())
                .collect(Collectors.toList());
        courseStatisticsResourceVo.setImgResource(String.valueOf(courseResourceList.stream().filter(courseResource ->
                courseResource.getResourceType().equals(ResourceType.IMAGE)).count()));
        courseStatisticsResourceVo.setCoursewareResource(String.valueOf(courseResourceList.stream().filter(courseResource ->
                courseResource.getResourceType().equals(ResourceType.TEACHING_COURSE_WARE)).count()));
        courseStatisticsResourceVo.setVideoResource(String.valueOf(courseResourceList.stream().filter(courseResource ->
                courseResource.getResourceType().equals(ResourceType.MICRO_VIDEO)).count()));
        courseStatisticsResourceVo.setDiscussTheme(String.valueOf(courseResourceList.stream().filter(courseResource ->
                courseResource.getResourceType().equals(ResourceType.THEME)).count()));
        courseStatisticsResourceVo.setAssessmentTest(String.valueOf(courseResourceList.stream().filter(courseResource ->
                courseResource.getResourceType().equals(ResourceType.CLASS_TEST)).count()));
        courseStatisticsResourceVo.setTotalResource(String.valueOf(courseResourceList.size()));
        List<CourseStatisticsResourceTopVo> courseStatisticsResourceTopVos = new ArrayList<>();
        final int ten = 10;
        finalCourseResourceList.stream().filter(item -> item.getIsPublic()).limit(ten).forEach(item -> {
            CourseStatisticsResourceTopVo courseStatisticsResourceTopVo = new CourseStatisticsResourceTopVo();
            courseStatisticsResourceTopVo.setResourceName(item.getResourceName());
            courseStatisticsResourceTopVo.setUpdateId(item.getCreatorId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            courseStatisticsResourceTopVo.setUpdateTime(sdf.format(item.getCreateTime()));
            courseStatisticsResourceTopVo.setUpdateName(item.getCreatorName());
            courseStatisticsResourceTopVos.add(courseStatisticsResourceTopVo);
        });
        courseStatisticsResourceVo.setTopTen(courseStatisticsResourceTopVos);
        return courseStatisticsResourceVo;
    }

    public CourseStatisticsKnowledgeVo getCourseStatisticsKnowledge(String courseId, String versionId) {
        CourseStatisticsKnowledgeVo courseStatisticsKnowledgeVo = new CourseStatisticsKnowledgeVo();
        CourseKnowledgeGraphDomain courseKnowledgeGraphDomain =
                courseKnowledgeGraphService.getCourseKnowledgeGraphDomain(courseId);
        /*获取对应课程知识节点列表*/
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(courseKnowledgeGraphDomain)){
            courseKnowledgeGraphNodeList = courseKnowledgeGraphService.getCourseKnowledgeGraphNodeList(courseKnowledgeGraphDomain);
        }
        courseStatisticsKnowledgeVo.setKonwledgeTotal(String.valueOf(courseKnowledgeGraphNodeList.size()));
        List<CourseKnowledgePointResource> courseKnowledgePointResources =
                courseKnowledgeStructureService.getCourseKnowledgePointResource(courseId, versionId, null);
        courseStatisticsKnowledgeVo.setKnowledgeNum(String.valueOf(courseKnowledgePointResources.size()));
        final int three = 3;
        courseKnowledgePointResources = courseKnowledgePointResources.stream()
                .sorted((a, b) -> Integer.compare(b.getResourceList().size(), a.getResourceList().size()))
                .limit(three)
                .collect(Collectors.toList());
        List<CourseStatisticsKnowledgeTopVo> courseStatisticsKnowledgeTopVos = new ArrayList<>();
        courseKnowledgePointResources.forEach(item -> {
            CourseStatisticsKnowledgeTopVo courseStatisticsKnowledgeTopVo = new CourseStatisticsKnowledgeTopVo();
            courseStatisticsKnowledgeTopVo.setKnowledgeContent(item.getPointContent());
            courseStatisticsKnowledgeTopVo.setKonwledgeRelationNum(String.valueOf(item.getResourceList().size()));
            courseStatisticsKnowledgeTopVos.add(courseStatisticsKnowledgeTopVo);
        });
        courseStatisticsKnowledgeVo.setTopThree(courseStatisticsKnowledgeTopVos);
        return courseStatisticsKnowledgeVo;
    }

    public Integer getCourseCountByCourseTypeSchoolYearTerm(Integer courseKind, String schoolYear, Integer term) {

        String sql = "select count(*) as \"count\" from (" +
                "select distinct course_id from tb_course_table a inner join tb_course_table_detail b " +
                "on a.id = b.course_table_id  where school_year= :schoolYear and term = :term  " +
                "and b.course_kind = :courseKind " +
                "group by a.id HAVING  count(b.id)>0" +
                ") a ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", schoolYear);
        paramMap.put("term", term);
        paramMap.put("courseKind", courseKind);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return Integer.valueOf(((Map) query.getSingleResult()).get("count") + "");
    }

    public Integer getCourseProjectBySchoolYearTerm(String schoolYear, Integer term) {
        String sql = " select count(*) as \"count\" from ( " +
                " select  DISTINCT a.id from tb_course_table a " +
                " inner join tb_project_attribute c on a.course_id= c.data_id " +
                " inner join  tb_project b on c.project_id = b.id " +
                " where school_year= :schoolYear and term = :term and  c.attribute_kind = 2 " +
                " ) a ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolYear", schoolYear);
        paramMap.put("term", term);
        Query query = entityManager.createNativeQuery(sql);
        paramMap.forEach(query::setParameter);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return Integer.valueOf(((Map) query.getSingleResult()).get("count") + "");
    }
}
