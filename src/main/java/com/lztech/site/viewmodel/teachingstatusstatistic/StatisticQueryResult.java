package com.lztech.site.viewmodel.teachingstatusstatistic;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Intellij IDEA.
 * User:  Bing
 * Date:  2021/9/18
 *
 * 用于接收查询结果的VO
 */
@Data
@Entity
public class StatisticQueryResult implements Serializable {

    @Id
    private Integer queryResultNum;
}
