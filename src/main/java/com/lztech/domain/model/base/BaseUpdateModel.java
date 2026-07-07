package com.lztech.domain.model.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseUpdateModel extends BaseModel {
    private String modifierId; // 更新人id
    private String modifierName; //修改人姓名
    private Date modifyTime;// 更新时间

}
