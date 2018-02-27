package com.kafkaiot.model;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "IDX_SenlabTtime", columnList = "messageDate"),
        @Index(name = "IDX_SenlabTdevEUI", columnList = "devEUI")})
public class SenlabTEntity extends BaseEntity {
    @Column
    private int temperature;
    @Column
    private double tempC;

    public SenlabTEntity() {
    }

    public SenlabTEntity(String messageDate, String devEUI, String fPort, int temperature) {
        super(DateTime.parse(messageDate).toDate(), devEUI, Integer.valueOf(fPort), 1);
        this.temperature = temperature;
        this.tempC = temperature / 16;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }
}
