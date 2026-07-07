package com.lztech.site.viewmodel.classroom;

import lombok.Data;

/**
 * ClassRoomResource
 */
@Data
public class ClassRoomResource {
    private String id = null;

    private String parentId = null;

    private String roomName = null;

    private Integer roomType = null;

    private Boolean hasLive = null;


}

