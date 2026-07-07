package com.lztech.site.service.authorityapi;

import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.administratorclass.AdministrativeClassIdVo;
import com.lztech.site.viewmodel.administratorclass.UserIdVo;
import com.lztech.site.viewmodel.authorityapi.*;
import com.lztech.site.viewmodel.userinfo.UserIdAndOpenIdVo;
import com.lztech.site.viewmodel.userinfo.Users;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.lztech.site.constants.ConstantWebApi.*;

@Service
public class AuthorityApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityApiService.class);
    @Value("${request.address.authorityApi}")
    private String authorityApi;
    @Value("${signKey}")
    private String signKey;

    public List<AdministrativeClassIdVo> getAdministrativeClassIdsByUserIds(List<String> userIds) {
        RestTemplate restTemplate = new RestTemplate();
        String url = authorityApi + GET_USERS_ADMINISTRATIVE_CLASS_IDS + "?validCode=" + Md5Utils.md5(signKey);

        List<UserIdVo> userIdVos = userIds.stream().map(s -> {
            UserIdVo userIdVo = new UserIdVo();
            userIdVo.setUserId(s);
            return userIdVo;
        }).collect(Collectors.toList());
        try {
            //设置Http的Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<List<UserIdVo>> httpEntity = new HttpEntity<>(userIdVos, headers);
            ResponseEntity<List<AdministrativeClassIdVo>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
                    new ParameterizedTypeReference<List<AdministrativeClassIdVo>>() {});
            return responseEntity.getBody();
        } catch (Exception e) {
            LOGGER.error("getAdministrativeClassIdsByUserIds->", e);
            return new ArrayList<>();
        }
    }


    public List<UsersInfoResource> getUsersInfo(List<Users> usersList) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<Users>> request = new HttpEntity<>(usersList, headers);
        String url = authorityApi + GET_USER_INFO + "?" + "validCode=" + key;
        try {
            ResponseEntity<List<UsersInfoResource>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request,
                    new ParameterizedTypeReference<List<UsersInfoResource>>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            LOGGER.error("getUsersInfo->", e);
            return new ArrayList<>();
        }

    }

    public List<UsersInfoResource> getUsersInfoResource(List<UserIdAndOpenIdVo> usersList) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<UserIdAndOpenIdVo>> request = new HttpEntity<>(usersList, headers);
        String url = authorityApi + GET_USER_INFO + "?" + "validCode=" + key;
        try {
            ResponseEntity<List<UsersInfoResource>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request,
                    new ParameterizedTypeReference<List<UsersInfoResource>>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            LOGGER.error("getUsersInfo->", e);
            return new ArrayList<>();
        }

    }

    public List<UserResource> getAllUser() {
        RestTemplate restTemplate = new RestTemplate();
        String url = authorityApi + ConstantWebApi.GET_ALL_USER + "?validCode=" + Md5Utils.md5(signKey);
        try {
            ResponseEntity<List<UserResource>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<UserResource>>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            LOGGER.error("getAllUser->", e);
            return null;
        }
    }

    public List<UserBaseInfoAndCollege> getTeacherInfoListByTeacherNo(List<String> teacherNoList) {
        List<String> distinctTeacherNoList = Arrays.asList(String.join(",", teacherNoList).split(","));
        List<UserBaseInfoAndCollege> teacherInfoVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(distinctTeacherNoList)) {
            String teacherNo = distinctTeacherNoList.stream().distinct().collect(Collectors.joining(","));
            RestTemplate restTemplate = new RestTemplate();

            String url = authorityApi + GET_TEACHER_INFO_BY_NO + "?userNos=" + teacherNo + "&validCode=" +
                    Md5Utils.md5(signKey);
            try {
                ResponseEntity<List<UserBaseInfoAndCollege>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<UserBaseInfoAndCollege>>() {
                        });
                return responseEntity.getBody();
            } catch (Exception e) {

                LOGGER.error("getTeacherInfoListByTeacherNo->", e);
            }
        }
        return teacherInfoVoList;
    }

    public List<TeacherVo> getAllTeachers() {
        RestTemplate restTemplate = new RestTemplate();
        String url = authorityApi + ConstantWebApi.GET_ALL_TEACHERS + "?validCode=" + Md5Utils.md5(signKey);

        try {
            ResponseEntity<List<TeacherVo>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<TeacherVo>>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            LOGGER.error("getAllTeachers->", e);
            return null;
        }
    }

    public List<StudentVo> getAllStudent() {
        RestTemplate restTemplate = new RestTemplate();
        String url = authorityApi + ConstantWebApi.GET_ALL_STUDENTS + "?validCode=" + Md5Utils.md5(signKey);

        try {
            ResponseEntity<List<StudentVo>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<StudentVo>>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            LOGGER.error("getAllStudent->", e);
            return null;
        }
    }

}
