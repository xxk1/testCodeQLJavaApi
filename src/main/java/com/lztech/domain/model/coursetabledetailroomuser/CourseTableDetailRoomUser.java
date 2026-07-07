package com.lztech.domain.model.coursetabledetailroomuser;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.coursetabledetail.CourseTableDetail;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_course_table_detail_room_user")
public class CourseTableDetailRoomUser extends BaseModel {
    @Id
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    private String id;
    /**
     * 教室Id
     */
    private String roomId;
    /**
     * 教室名称
     */
    private String roomName;
    /**
     * 教室编号
     */
    private String roomCode;
    /**
     * 人员数量
     */
    private String personnelNumber;
    /**
     * 楼栋Id
     */
    private String roomCategoryId;
    /**
     * 课表明细Id
     */
    @ManyToOne
    @JoinColumn(name = "course_table_detail_id")
    private CourseTableDetail courseTableDetail;

    /**
     * 物品准备老师id
     */
    private String itemTeacherId;

    /**
     * 物品准备老师编号
     */
    private String itemTeacherNo;

    /**
     * 物品准备老师名称
     */
    private String itemTeacherName;
    /**
     * 是否直播
     */
    private boolean hasLive;
    /**
     * 教室关联组
     */
    private String groupId;

}

