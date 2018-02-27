
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "pro",
        "ver"
})
public class Alr {

    @JsonProperty("pro")
    private String pro;
    @JsonProperty("ver")
    private String ver;

    @JsonProperty("pro")
    public String getPro() {
        return pro;
    }

    @JsonProperty("pro")
    public void setPro(String pro) {
        this.pro = pro;
    }

    @JsonProperty("ver")
    public String getVer() {
        return ver;
    }

    @JsonProperty("ver")
    public void setVer(String ver) {
        this.ver = ver;
    }

}
