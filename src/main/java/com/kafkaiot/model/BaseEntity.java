package com.kafkaiot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(indexes = {@Index(name = "IDX_time", columnList = "messageDate"),
        @Index(name = "IDX_devEUI", columnList = "devEUI")})
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column
    @NotNull
    private Date messageDate;
    @Column
    @NotNull
    private String devEUI;
    @Column
    @NotNull
    private Integer fPort;
    @Column
    @NotNull
    private Integer type;

    BaseEntity() {
    }

    BaseEntity(Date messageDate, String devEUI, Integer fPort, int type) {
        this.messageDate = messageDate;
        this.devEUI = devEUI;
        this.fPort = fPort;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public void setDevEUI(String devEUI) {
        this.devEUI = devEUI;
    }

    public Integer getfPort() {
        return fPort;
    }

    public void setfPort(Integer fPort) {
        this.fPort = fPort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
