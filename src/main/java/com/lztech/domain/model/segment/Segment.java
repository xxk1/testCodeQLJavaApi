package com.lztech.domain.model.segment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbSegment")
public class Segment {
    @EmbeddedId
    protected SegmentPK segmentPK;
    protected String buildName;
    protected String startTime;
    protected String endTime;
    protected String description;
    protected String recordStartTime;
    protected String recordEndTime;

    @Column(name = "segment")
    public Integer getSegment() {
        return segmentPK.getSegment();
    }

    @Column(name = "buildID")
    public String getBuildID() {
        return segmentPK.getBuildID();
    }


}
