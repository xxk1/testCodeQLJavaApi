package com.lztech.site.service.questionlibrary;

import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryPageQueryParm;
import com.lztech.site.viewmodel.questionlibrary.QuestionLibraryPageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.lztech.site.config.Access.signKey;

@Service
public class QuestionLibraryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionLibraryService.class);
    @Value("${request.address.teachingApi}")
    private String teachingApi;

    public QuestionLibraryPageResource getQuestionLibraryPage(String courseId,String structureId,Integer page,Integer pageSize) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey).toLowerCase();
        QuestionLibraryPageResource questionLibraryResource = new QuestionLibraryPageResource();
        String url = teachingApi + ConstantWebApi.GET_QUESTION_LIBRARY +"?validCode=" + key;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        QuestionLibraryPageQueryParm questionLibraryPageParm = new QuestionLibraryPageQueryParm();
        questionLibraryPageParm.setCourseId(courseId);
        questionLibraryPageParm.setCourseStructureId(structureId);
        questionLibraryPageParm.setPage(page);
        questionLibraryPageParm.setPageSize(pageSize);
        HttpEntity<QuestionLibraryPageQueryParm> httpEntity = new HttpEntity<>(questionLibraryPageParm, headers);
        try {
            ResponseEntity<QuestionLibraryPageResource> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
                    new ParameterizedTypeReference<QuestionLibraryPageResource>() {
                    });
            questionLibraryResource = responseEntity.getBody();
            return questionLibraryResource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
