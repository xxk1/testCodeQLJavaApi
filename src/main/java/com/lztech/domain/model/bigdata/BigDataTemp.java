package com.lztech.domain.model.bigdata;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "big_data_temp")
public class BigDataTemp {

    @Id
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    protected String id;

    private int resourceBrowsingNum = 0;

    private int resourceDownloadNum = 0;

    private double lastCompletionRate = 0.0;
}
