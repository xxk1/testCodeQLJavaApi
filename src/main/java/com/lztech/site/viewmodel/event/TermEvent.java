package com.lztech.site.viewmodel.event;

import lombok.Data;

@Data
public class TermEvent {
    private String id;
    private Integer source;
    private String schoolYear;
    private Integer term;
    private String startDate;
    private String endDate;
    private String description;
    private String modifyTime;
}
