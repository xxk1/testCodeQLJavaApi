package com.lztech.site.viewmodel.superviseevaluation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SupervisionCourseTempResource {
    private String teachers;
    private Date courseDate;
    private String segment;
    private String roomId;
    private String roomName;
    private String courseTableDetailId;
    private String segmentStartTime;
    private String segmentEndTime;
    private String groupId;
}
