package com.lztech.site.service.administratorclass;

import com.lztech.domain.model.administrativeclass.AdministrativeClass;
import com.lztech.domain.model.administrativeclassandgroup.AdministrativeClassAndGroup;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.major.Major;
import com.lztech.persistence.repositories.administrativeclassandgroup.AdministratorClassAndGroupRepository;
import com.lztech.persistence.repositories.administratorclass.AdministratorClassRepository;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.persistence.repositories.major.MajorRepository;
import com.lztech.site.resource.administratorclass.AdministrativeClassGroupInfo;
import com.lztech.site.resource.administratorclass.AdministrativeClassInfo;
import com.lztech.site.resource.administratorclass.AdministrativeClassInfoVo;
import com.lztech.site.resource.administratorclass.AdministrativeClassVo;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.administratorclass.AdministrativeClassResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AdministratorClassService {
    @Autowired
    private AdministratorClassRepository administratorClassRepository;

    @Autowired
    private AdministratorClassAndGroupRepository administratorClassAndGroupRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MajorRepository majorRepository;


    public List<AdministrativeClass> saveAdministratorClasses(List<AdministrativeClassInfo> administrativeClassInfos) {
        List<AdministrativeClass> administrativeClassList = new ArrayList<>();
        administrativeClassInfos.forEach(administrativeClassInfo -> {
            AdministrativeClass administrativeClass = new AdministrativeClass();
            administrativeClass.setId(administrativeClassInfo.getClassId());
            administrativeClass.setClassName(administrativeClassInfo.getClassName());
            administrativeClass.setCollegeId(administrativeClassInfo.getCollegeId());
            administrativeClass.setCollegeName(administrativeClassInfo.getCollegeName());
            administrativeClass.setGradeMajorCode(administrativeClassInfo.getGradeMajorCode());
            administrativeClass.setMajorId(administrativeClassInfo.getMajorId());
            administrativeClass.setMajorName(administrativeClassInfo.getMajorName());
            administrativeClass.setSchoolSystem(administrativeClassInfo.getSchoolSystem());
            administrativeClass.setYear(administrativeClassInfo.getYear());
            administrativeClass.setStudentCount(Integer.valueOf(administrativeClassInfo.getStudentNum()));
            administrativeClass.setSource(Source.DATA_DOCKING);
            administrativeClassList.add(administrativeClass);
        });
        return administratorClassRepository.saveAll(administrativeClassList);

    }

    public List<AdministrativeClassAndGroup> saveAdministrativeClassAndGroup(List<AdministrativeClassGroupInfo> administrativeClassGroupInfos) {
        List<AdministrativeClassAndGroup> administrativeClassAndGroupList = new ArrayList<>();
        List<College> collegeList = collegeRepository.findAll();
        List<Major> majorList = majorRepository.findAll();
        List<Group> groupList = groupRepository.findAll();
        List<AdministrativeClass> administrativeClassList = administratorClassRepository.findAll();
        administrativeClassGroupInfos.forEach(administrativeClassGroupInfo -> {
            AdministrativeClassAndGroup administrativeClassAndGroup = new AdministrativeClassAndGroup();
            administrativeClassAndGroup.setId(administrativeClassGroupInfo.getId());
            administrativeClassAndGroup.setClassName(administrativeClassGroupInfo.getClassName());
            College college = collegeList.stream().filter(c -> c.getId()
                    .equals(administrativeClassGroupInfo.getCollegeId())).findFirst().orElse(null);
            administrativeClassAndGroup.setCollege(college);
            Major major = majorList.stream().filter(m -> m.getId()
                    .equals(administrativeClassGroupInfo.getMajorId())).findFirst().orElse(null);
            administrativeClassAndGroup.setMajor(major);
            Group group = groupList.stream().filter(g -> g.getGroupNo()
                    .equals(administrativeClassGroupInfo.getGroupNo())).findFirst().orElse(null);
            administrativeClassAndGroup.setGroup(group);
            AdministrativeClass administrativeClass = administrativeClassList.stream().filter(a -> a.getId()
                    .equals(administrativeClassGroupInfo.getClassId())).findFirst().orElse(null);
            administrativeClassAndGroup.setAdministrativeClass(administrativeClass);
            administrativeClassAndGroup.setTeacherName(administrativeClassGroupInfo.getTeacherName());
            administrativeClassAndGroup.setTeacherId(administrativeClassGroupInfo.getTeacherId());
            administrativeClassAndGroup.setCreateTime(TimeUtils.getNowDateTime());
            administrativeClassAndGroup.setSource(Source.DATA_DOCKING);
            administrativeClassAndGroup.setYear(administrativeClassGroupInfo.getYear());
            administrativeClassAndGroup.setStudentsCount(Integer.valueOf(administrativeClassGroupInfo.getStudentNum()));
            administrativeClassAndGroupList.add(administrativeClassAndGroup);
        });
        return administratorClassAndGroupRepository.saveAll(administrativeClassAndGroupList);
    }

    public List<AdministrativeClassVo> getAdministrativeClassVoList(String teacherId) {
        List<AdministrativeClass> administrativeClassList =
                StringUtils.isEmpty(teacherId) ? administratorClassRepository.findAll() :
                        administratorClassRepository.findByTeacherId(teacherId);
        List<AdministrativeClassVo> administrativeClassVoList = administrativeClassList.stream().map(
                administrativeClass -> new AdministrativeClassVo() {{
                    this.className(administrativeClass.getClassName());
                    this.id(administrativeClass.getId());
                    this.collegeCode(administrativeClass.getCollegeId());
                    this.collegeName(administrativeClass.getCollegeName());
                    this.year(administrativeClass.getYear());
                    this.teacherId(administrativeClass.getTeacherId());
                    this.majorId(administrativeClass.getMajorId());
                    this.majorCode(administrativeClass.getGradeMajorCode());
                    this.majorName(administrativeClass.getMajorName());
                }}
        ).collect(Collectors.toList());
        return administrativeClassVoList;
    }

    public List<AdministrativeClassInfoVo> getAdministrativeClassVoListByTeacherId(String teacherId) {
        List<AdministrativeClass> administrativeClassList = administratorClassRepository.findByCounsellorId(teacherId);
        List<AdministrativeClassInfoVo> administrativeClassInfoVoList = new ArrayList<>();
        for (AdministrativeClass administrativeClass : administrativeClassList) {
            AdministrativeClassInfoVo administrativeClassInfoVo = new AdministrativeClassInfoVo();
            administrativeClassInfoVo.setId(administrativeClass.getId());
            administrativeClassInfoVo.setCounsellorId(administrativeClass.getCounsellorId());
            administrativeClassInfoVo.setCounsellorNo(administrativeClass.getCounsellorNo());
            administrativeClassInfoVo.setCounsellorName(administrativeClass.getCounsellorName());
            administrativeClassInfoVo.setClassName(administrativeClass.getClassName());
            administrativeClassInfoVo.setYear(administrativeClass.getYear());
            administrativeClassInfoVo.setCollegeId(administrativeClass.getCollegeId());
            administrativeClassInfoVo.setCollegeName(administrativeClass.getCollegeName());
            administrativeClassInfoVo.setMajorId(administrativeClass.getMajorId());
            administrativeClassInfoVo.setMajorCode(administrativeClass.getGradeMajorCode());
            administrativeClassInfoVo.setMajorName(administrativeClass.getMajorName());
            administrativeClassInfoVoList.add(administrativeClassInfoVo);
        }
        sortAdministrativeClassList(administrativeClassInfoVoList);
        return administrativeClassInfoVoList;
    }
    public  void sortAdministrativeClassList(List<AdministrativeClassInfoVo> administrativeClassInfoVoList) {
        // 创建中文排序器
        Collator collator = Collator.getInstance(Locale.CHINA);
        // 使用Lambda表达式进行排序
        administrativeClassInfoVoList.sort((o1, o2) -> collator.compare(o1.getClassName(), o2.getClassName()));
    }
    public List<AdministrativeClassResourceVo> getAdministrativeClassList(String collegeId, String className) {
        String classNameLike = "%" + CharactersReplace.replaceCharacters(className.trim()) + "%";
        List<AdministrativeClass> administrativeClassList = administratorClassRepository
                .findByCollegeIdAndClassNameLikeOrderById(collegeId, classNameLike);
        List<AdministrativeClassResourceVo> administrativeClassResourceVoList = new ArrayList<>();
        administrativeClassList.forEach(administrativeClass -> {
            AdministrativeClassResourceVo administrativeClassResourceVo = new AdministrativeClassResourceVo();
            administrativeClassResourceVo.setClassId(administrativeClass.getId());
            administrativeClassResourceVo.setClassName(administrativeClass.getClassName());
            administrativeClassResourceVoList.add(administrativeClassResourceVo);
        });
        return administrativeClassResourceVoList;
    }

}
