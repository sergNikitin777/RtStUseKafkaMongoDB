
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "Lrrid",
        "Chain",
        "LrrRSSI",
        "LrrSNR",
        "LrrESP"
})
public class Lrr {

    @JsonProperty("Lrrid")
    private String lrrid;
    @JsonProperty("Chain")
    private String chain;
    @JsonProperty("LrrRSSI")
    private String lrrRSSI;
    @JsonProperty("LrrSNR")
    private String lrrSNR;
    @JsonProperty("LrrESP")
    private String lrrESP;

    @JsonProperty("Lrrid")
    public String getLrrid() {
        return lrrid;
    }

    @JsonProperty("Lrrid")
    public void setLrrid(String lrrid) {
        this.lrrid = lrrid;
    }

    @JsonProperty("Chain")
    public String getChain() {
        return chain;
    }

    @JsonProperty("Chain")
    public void setChain(String chain) {
        this.chain = chain;
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

    @JsonProperty("LrrESP")
    public String getLrrESP() {
        return lrrESP;
    }

    @JsonProperty("LrrESP")
    public void setLrrESP(String lrrESP) {
        this.lrrESP = lrrESP;
    }

}
