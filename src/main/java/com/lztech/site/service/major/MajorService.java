package com.lztech.site.service.major;

import com.lztech.domain.model.college.College;
import com.lztech.domain.model.college.enums.CourseInfoCollege;
import com.lztech.domain.model.college.enums.CourseTableCollege;
import com.lztech.domain.model.major.Major;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.major.MajorRepository;
import com.lztech.site.resource.major.MajorInfo;
import com.lztech.site.until.CharactersReplace;
import com.lztech.site.viewmodel.major.MajorResourceVo;
import com.lztech.site.viewmodel.major.MajorVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    public List<Major> saveMajorList(List<MajorInfo> majorInfos) {
        List<Major> majorList = new ArrayList<>();
        majorInfos.forEach(majorInfo -> {
            Major major = new Major();
            major.setId(majorInfo.getMajorId());
            major.setMajorName(majorInfo.getMajorName());
            major.setYear(majorInfo.getYear());
            major.setCollege(getCollege(majorInfo.getCollegeName(), majorInfo.getCollegeCode()));
            majorList.add(major);
        });
        return majorRepository.saveAll(majorList);
    }

    private College getCollege(String collegeName, String collegeCode) {
        College college = collegeRepository.findByCollegeName(collegeName);
        if (Objects.isNull(college)) {
            college = new College();
            college.setId(UUID.randomUUID().toString());
            college.setCollegeCode(collegeCode);
            college.setCollegeName(collegeName);
            college.setCourseInfoCollege(CourseInfoCollege.TRUE);
            college.setCourseTableCollege(CourseTableCollege.TRUE);
            collegeRepository.save(college);
        }
        return college;
    }

    public ResponseEntity<MajorVo> getMajorsList(String majorName, String collegeName, Integer page, Integer pageSize) {
        List<MajorResourceVo> majorVoList = new ArrayList<>();
        Page<Major> majorPage = null;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        if (!StringUtils.isEmpty(majorName) && StringUtils.isEmpty(collegeName)) {
            String name = CharactersReplace.replaceCharacters(majorName.trim());
            majorName = "%" + name + "%";
            majorPage = majorRepository.findByMajorNameLikeOrderByIdAsc(majorName, pageable);
        } else if (!StringUtils.isEmpty(collegeName) && StringUtils.isEmpty(majorName)) {
            String name = CharactersReplace.replaceCharacters(collegeName.trim());
            collegeName = "%" + name + "%";
            majorPage = majorRepository.findByCollegeCollegeNameIsLikeOrderByIdAsc(collegeName, pageable);
        } else if (!StringUtils.isEmpty(collegeName) && !StringUtils.isEmpty(majorName)) {
            String queryCollegeName = CharactersReplace.replaceCharacters(collegeName.trim());
            String queryMajorName = CharactersReplace.replaceCharacters(majorName.trim());
            queryMajorName = "%" + queryMajorName + "%";
            queryCollegeName = "%" + queryCollegeName + "%";
            majorPage = majorRepository.findByCollegeNameMajorNameOrderBy(queryMajorName, queryCollegeName, pageable);
        } else {
            majorPage = majorRepository.findAll(pageable);
        }
        if (majorPage != null && CollectionUtils.isNotEmpty(majorPage.getContent())) {
            for (Major major : majorPage.getContent()) {
                MajorResourceVo majorResourceVo = new MajorResourceVo();
                majorResourceVo.setMajorId(major.getId());
                majorResourceVo.setCollegeName(major.getCollege().getCollegeName());
                majorResourceVo.setMajorName(major.getMajorName());
                majorResourceVo.setYear(major.getYear());
                majorVoList.add(majorResourceVo);
            }
        }
        int total = (int) majorPage.getTotalElements();
        int pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        MajorVo majorVo = new MajorVo();
        majorVo.setTotal(total);
        majorVo.setPage(page);
        majorVo.setMajorResourcesList(majorVoList);
        majorVo.setPageSize(pageSize);
        majorVo.setPageCount(pageCount);
        return new ResponseEntity(majorVo, HttpStatus.OK);
    }
}

