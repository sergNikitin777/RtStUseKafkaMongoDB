
package com.kafkaiot.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "alr"
})
public class CustomerData {

    @JsonProperty("alr")
    private Alr alr;

    @JsonProperty("alr")
    public Alr getAlr() {
        return alr;
    }

    @JsonProperty("alr")
    public void setAlr(Alr alr) {
        this.alr = alr;
    }

}
