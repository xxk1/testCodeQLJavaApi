package com.lztech.domain.model.segment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
@Embeddable
public class SegmentPK implements Serializable {
    protected String buildID;
    protected Integer segment;

    public SegmentPK() {
    }

    public SegmentPK(String buildID, Integer segment) {
        this.buildID = buildID;
        this.segment = segment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (segment == null ? 0 : segment.hashCode());
        result = prime * result + (buildID == null ? 0 : buildID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SegmentPK)) {
            return false;
        }
        SegmentPK objKey = (SegmentPK) obj;
        if (Objects.equals(segment, objKey.segment) &&
                buildID.equalsIgnoreCase(objKey.buildID)) {
            return true;
        }
        return false;
    }
}
