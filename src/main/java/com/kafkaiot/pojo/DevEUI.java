
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "DevEUI_uplink"
})
public class DevEUI {

    @JsonProperty("DevEUI_uplink")
    private DevEUIUplink devEUIUplink;

    @JsonProperty("DevEUI_uplink")
    public DevEUIUplink getDevEUIUplink() {
        return devEUIUplink;
    }

    @JsonProperty("DevEUI_uplink")
    public void setDevEUIUplink(DevEUIUplink devEUIUplink) {
        this.devEUIUplink = devEUIUplink;
    }

}
