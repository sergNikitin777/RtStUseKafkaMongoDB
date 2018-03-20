package com.kafkaiot.model;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "IDX_SenlabMtime", columnList = "messageDate"),
        @Index(name = "IDX_SenlabMdevEUI", columnList = "devEUI")})
public class SenlabMEntity extends BaseEntity {
    @Column
    private long count;

    public SenlabMEntity() {
    }

    public SenlabMEntity(String messageDate, String devEUI, String fPort, long count) {
        super(DateTime.parse(messageDate).toDate(), devEUI, Integer.valueOf(fPort), 2);
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SenlabMEntity{" +
                "count=" + count +
                '}';
    }
}
