
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "Time",
        "DevEUI",
        "FPort",
        "payload_hex",
        "CustomerID"
})
public class DevEUIUplink {

    @JsonProperty("Time")
    private String time;
    @JsonProperty("DevEUI")
    private String devEUI;
    @JsonProperty("FPort")
    private String fPort;
    @JsonProperty("payload_hex")
    private String payloadHex;
    @JsonProperty("CustomerID")
    private String customerID;

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

    @JsonProperty("payload_hex")
    public String getPayloadHex() {
        return payloadHex;
    }

    @JsonProperty("payload_hex")
    public void setPayloadHex(String payloadHex) {
        this.payloadHex = payloadHex;
    }

    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

}
