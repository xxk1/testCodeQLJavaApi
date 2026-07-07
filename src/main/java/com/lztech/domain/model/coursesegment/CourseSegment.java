package com.lztech.domain.model.coursesegment;

import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import com.lztech.domain.model.segment.Segment;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "tb_course_segment")
public class CourseSegment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    protected String id;
    @ManyToOne
    @JoinColumn(name = "course_table_detail_id")
    private CourseTableDetail courseTableDetail;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "build_ID", referencedColumnName = "buildID" ),
            @JoinColumn(name = "segment", referencedColumnName = "segment")
    })
    private Segment segment;

}
