package com.lztech.domain.model.experimentoriginalcoursetabledetail;

import com.lztech.domain.model.segment.Segment;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_experiment_original_course_table_detail_segment")
public class ExperimentOriginalCourseTableDetailSegment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    protected String id;

    /**
     * 课表明细Id
     */
    @ManyToOne
    @JoinColumn(name = "experiment_original_course_table_detail_id")
    private ExperimentOriginalCourseTableDetail experimentOriginalCourseTableDetail;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "build_ID", referencedColumnName = "buildID"),
            @JoinColumn(name = "segment", referencedColumnName = "segment")
    })
    private Segment segment;
}
