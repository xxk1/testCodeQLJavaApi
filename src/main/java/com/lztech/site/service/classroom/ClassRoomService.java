package com.lztech.site.service.classroom;

import com.lztech.site.constants.ConstantWebApi;
import com.lztech.site.until.Md5Utils;
import com.lztech.site.viewmodel.buildings.Build;
import com.lztech.site.viewmodel.buildings.Room;
import com.lztech.site.viewmodel.classroom.ClassRoomResource;
import com.lztech.site.viewmodel.classroom.ClassroomAndFloorResource;
import com.lztech.site.viewmodel.classroom.ClassroomBaseInfoVo;
import com.lztech.site.viewmodel.project.ProjectIdVo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.lztech.site.constants.ConstantWebApi.GET_BUILD_AND_ROOM;

@Service
public class ClassRoomService {

    @Value("${signKey}")
    private String signKey;
    @Value("${request.address.classRoom}")
    private String classRoomAddress;

    public List<ClassRoomResource> findClassRoomResourceList(String roomIds) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        String url = "";
        List<ClassRoomResource> classRoomResourceList = new ArrayList<>();
        if (StringUtils.isNotBlank(roomIds)) {
            url = classRoomAddress + ConstantWebApi.GET_CLASS_ROOMS + "?validCode=" + key + "&roomIds=" + roomIds;
        } else {
            url = classRoomAddress + ConstantWebApi.GET_CLASS_ROOMS + "?validCode=" + key + "&roomIds=";
        }
        ResponseEntity<List<ClassRoomResource>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ClassRoomResource>>() {
                });
        try {
            classRoomResourceList = responseEntity.getBody();
            return classRoomResourceList;
        } catch (Exception e) {
            e.printStackTrace();
            return classRoomResourceList;
        }

    }

    public List<ClassroomBaseInfoVo> getTeachingCenterClassroomList(String teachingCenterId) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        String url = "";
        List<ClassroomBaseInfoVo> classroomBaseInfoVoList = new ArrayList<>();
        if (StringUtils.isNotBlank(teachingCenterId)) {
            url = classRoomAddress + ConstantWebApi.GET_TEACHING_CENTER_CLASSROOM + "?validCode=" + key + "&teachingCenterId=" + teachingCenterId;
        } else {
            url = classRoomAddress + ConstantWebApi.GET_TEACHING_CENTER_CLASSROOM + "?validCode=" + key;
        }

        try {
            ResponseEntity<List<ClassroomBaseInfoVo>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<ClassroomBaseInfoVo>>() {
                    });
            classroomBaseInfoVoList = responseEntity.getBody();
            return classroomBaseInfoVoList;
        } catch (Exception e) {
            e.printStackTrace();
            return classroomBaseInfoVoList;
        }
    }


    public List<ClassroomAndFloorResource> getClassroomFloorInfo(List<String> roomIds) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<String>> request = new HttpEntity<>(roomIds, headers);
        String url = "";
        List<ClassroomAndFloorResource> classroomAndFloorList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roomIds)) {
            url = classRoomAddress + ConstantWebApi.GET_CLASSROOM_FLOOR_INFO + "?validCode=" + key;
        }
        ResponseEntity<List<ClassroomAndFloorResource>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request,
                new ParameterizedTypeReference<List<ClassroomAndFloorResource>>() {
                });
        try {
            classroomAndFloorList = responseEntity.getBody();
            return classroomAndFloorList;
        } catch (Exception e) {
            e.printStackTrace();
            return classroomAndFloorList;
        }

    }

    public ProjectIdVo getBookableProjectIds(String startTime, String endTime) {
        RestTemplate restTemplate = new RestTemplate();
        String key = Md5Utils.md5(signKey);
        String url = "";
        ProjectIdVo projectIdVo = new ProjectIdVo();
        url = classRoomAddress + ConstantWebApi.GET_BOOKABLE_PROJECT + "?validCode=" + key + "&startTime=" + startTime + "&endTime=" + endTime;
        try {
            ResponseEntity<ProjectIdVo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ProjectIdVo>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return projectIdVo;
        }
    }

    public List<Build> getBuild() {
        RestTemplate restTemplate = new RestTemplate();
        List<Build> builds = new ArrayList<>();
        String url = classRoomAddress + GET_BUILD_AND_ROOM + "?validCode=" + Md5Utils.md5(signKey);
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            for (Object object : Objects.requireNonNull(responseEntity.getBody())) {
                JSONObject jsonObject = JSONObject.fromObject(object);
                Build build = (Build) JSONObject.toBean(jsonObject, Build.class);
                List<Room> rooms = new ArrayList<>();
                for (Object item : build.getRooms()) {
                    JSONObject jsonObjectRoom = JSONObject.fromObject(item);
                    Room room = (Room) JSONObject.toBean(jsonObjectRoom, Room.class);
                    rooms.add(room);
                }
                build.setRooms(rooms);
                builds.add(build);
            }
        }
        return builds;
    }
}
