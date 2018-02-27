
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "Lrr"
})
public class Lrrs {

    @JsonProperty("Lrr")
    private List<Lrr> lrr = new ArrayList<>();

    @JsonProperty("Lrr")
    public List<Lrr> getLrr() {
        return lrr;
    }

    @JsonProperty("Lrr")
    public void setLrr(List<Lrr> lrr) {
        this.lrr = lrr;
    }

}
