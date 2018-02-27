
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "Time",
        "DevEUI",
        "FPort",
        "FCntUp",
        "ADRbit",
        "MType",
        "FCntDn",
        "payload_hex",
        "mic_hex",
        "Lrcid",
        "LrrRSSI",
        "LrrSNR",
        "SpFact",
        "SubBand",
        "Channel",
        "DevLrrCnt",
        "Lrrid",
        "Late",
        "LrrLAT",
        "LrrLON",
        "Lrrs",
        "CustomerID",
        "CustomerData",
        "ModelCfg",
        "AppSKey",
        "DevAddr"
})
public class DevEUIUplink {

    @JsonProperty("Time")
    private String time;
    @JsonProperty("DevEUI")
    private String devEUI;
    @JsonProperty("FPort")
    private String fPort;
    @JsonProperty("FCntUp")
    private String fCntUp;
    @JsonProperty("ADRbit")
    private String aDRbit;
    @JsonProperty("MType")
    private String mType;
    @JsonProperty("FCntDn")
    private String fCntDn;
    @JsonProperty("payload_hex")
    private String payloadHex;
    @JsonProperty("mic_hex")
    private String micHex;
    @JsonProperty("Lrcid")
    private String lrcid;
    @JsonProperty("LrrRSSI")
    private String lrrRSSI;
    @JsonProperty("LrrSNR")
    private String lrrSNR;
    @JsonProperty("SpFact")
    private String spFact;
    @JsonProperty("SubBand")
    private String subBand;
    @JsonProperty("Channel")
    private String channel;
    @JsonProperty("DevLrrCnt")
    private String devLrrCnt;
    @JsonProperty("Lrrid")
    private String lrrid;
    @JsonProperty("Late")
    private String late;
    @JsonProperty("LrrLAT")
    private String lrrLAT;
    @JsonProperty("LrrLON")
    private String lrrLON;
    @JsonProperty("Lrrs")
    private Lrrs lrrs;
    @JsonProperty("CustomerID")
    private String customerID;
    @JsonProperty("CustomerData")
    private CustomerData customerData;
    @JsonProperty("ModelCfg")
    private String modelCfg;
    @JsonProperty("AppSKey")
    private String appSKey;
    @JsonProperty("DevAddr")
    private String devAddr;

    @JsonProperty("Time")
    public String getTime() {
        return time;
    }

    @JsonProperty("Time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("DevEUI")
    public String getDevEUI() {
        return devEUI;
    }

    @JsonProperty("DevEUI")
    public void setDevEUI(String devEUI) {
        this.devEUI = devEUI;
    }

    @JsonProperty("FPort")
    public String getFPort() {
        return fPort;
    }

    @JsonProperty("FPort")
    public void setFPort(String fPort) {
        this.fPort = fPort;
    }

    @JsonProperty("FCntUp")
    public String getFCntUp() {
        return fCntUp;
    }

    @JsonProperty("FCntUp")
    public void setFCntUp(String fCntUp) {
        this.fCntUp = fCntUp;
    }

    @JsonProperty("ADRbit")
    public String getADRbit() {
        return aDRbit;
    }

    @JsonProperty("ADRbit")
    public void setADRbit(String aDRbit) {
        this.aDRbit = aDRbit;
    }

    @JsonProperty("MType")
    public String getMType() {
        return mType;
    }

    @JsonProperty("MType")
    public void setMType(String mType) {
        this.mType = mType;
    }

    @JsonProperty("FCntDn")
    public String getFCntDn() {
        return fCntDn;
    }

    @JsonProperty("FCntDn")
    public void setFCntDn(String fCntDn) {
        this.fCntDn = fCntDn;
    }

    @JsonProperty("payload_hex")
    public String getPayloadHex() {
        return payloadHex;
    }

    @JsonProperty("payload_hex")
    public void setPayloadHex(String payloadHex) {
        this.payloadHex = payloadHex;
    }

    @JsonProperty("mic_hex")
    public String getMicHex() {
        return micHex;
    }

    @JsonProperty("mic_hex")
    public void setMicHex(String micHex) {
        this.micHex = micHex;
    }

    @JsonProperty("Lrcid")
    public String getLrcid() {
        return lrcid;
    }

    @JsonProperty("Lrcid")
    public void setLrcid(String lrcid) {
        this.lrcid = lrcid;
    }

    @JsonProperty("LrrRSSI")
    public String getLrrRSSI() {
        return lrrRSSI;
    }

    @JsonProperty("LrrRSSI")
    public void setLrrRSSI(String lrrRSSI) {
        this.lrrRSSI = lrrRSSI;
    }

    @JsonProperty("LrrSNR")
    public String getLrrSNR() {
        return lrrSNR;
    }

    @JsonProperty("LrrSNR")
    public void setLrrSNR(String lrrSNR) {
        this.lrrSNR = lrrSNR;
    }

    @JsonProperty("SpFact")
    public String getSpFact() {
        return spFact;
    }

    @JsonProperty("SpFact")
    public void setSpFact(String spFact) {
        this.spFact = spFact;
    }

    @JsonProperty("SubBand")
    public String getSubBand() {
        return subBand;
    }

    @JsonProperty("SubBand")
    public void setSubBand(String subBand) {
        this.subBand = subBand;
    }

    @JsonProperty("Channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("Channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("DevLrrCnt")
    public String getDevLrrCnt() {
        return devLrrCnt;
    }

    @JsonProperty("DevLrrCnt")
    public void setDevLrrCnt(String devLrrCnt) {
        this.devLrrCnt = devLrrCnt;
    }

    @JsonProperty("Lrrid")
    public String getLrrid() {
        return lrrid;
    }

    @JsonProperty("Lrrid")
    public void setLrrid(String lrrid) {
        this.lrrid = lrrid;
    }

    @JsonProperty("Late")
    public String getLate() {
        return late;
    }

    @JsonProperty("Late")
    public void setLate(String late) {
        this.late = late;
    }

    @JsonProperty("LrrLAT")
    public String getLrrLAT() {
        return lrrLAT;
    }

    @JsonProperty("LrrLAT")
    public void setLrrLAT(String lrrLAT) {
        this.lrrLAT = lrrLAT;
    }

    @JsonProperty("LrrLON")
    public String getLrrLON() {
        return lrrLON;
    }

    @JsonProperty("LrrLON")
    public void setLrrLON(String lrrLON) {
        this.lrrLON = lrrLON;
    }

    @JsonProperty("Lrrs")
    public Lrrs getLrrs() {
        return lrrs;
    }

    @JsonProperty("Lrrs")
    public void setLrrs(Lrrs lrrs) {
        this.lrrs = lrrs;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("CustomerData")
    public CustomerData getCustomerData() {
        return customerData;
    }

    @JsonProperty("CustomerData")
    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    @JsonProperty("ModelCfg")
    public String getModelCfg() {
        return modelCfg;
    }

    @JsonProperty("ModelCfg")
    public void setModelCfg(String modelCfg) {
        this.modelCfg = modelCfg;
    }

    @JsonProperty("AppSKey")
    public String getAppSKey() {
        return appSKey;
    }

    @JsonProperty("AppSKey")
    public void setAppSKey(String appSKey) {
        this.appSKey = appSKey;
    }

    @JsonProperty("DevAddr")
    public String getDevAddr() {
        return devAddr;
    }

    @JsonProperty("DevAddr")
    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
    }

}
