package com.kafkaiot.model;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "IDX_SenlabHtime", columnList = "messageDate"),
        @Index(name = "IDX_SenlabHdevEUI", columnList = "devEUI")})
public class SenlabHEntity extends BaseEntity {
    @Column
    private int temperature;
    @Column
    private int humidity;
    @Column
    private double tempC;

    public SenlabHEntity() {
    }

    public SenlabHEntity(String messageDate, String devEUI, String fPort, int temperature, int humidity) {
        super(DateTime.parse(messageDate).toDate(), devEUI, Integer.valueOf(fPort), 3);
        this.temperature = temperature;
        this.humidity = humidity;
        this.tempC = temperature / 16;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    @Override
    public String toString() {
        return "SenlabHEntity{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", tempC=" + tempC +
                '}';
    }
}
