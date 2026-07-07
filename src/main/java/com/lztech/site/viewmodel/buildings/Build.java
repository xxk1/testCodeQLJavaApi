package com.lztech.site.viewmodel.buildings;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Build {
    private String buildId;
    private String buildName;
    private String roomCategoryType;
    private List<Room> rooms;
}

