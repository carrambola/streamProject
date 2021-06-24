package org.example.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamInfoDataMain {

    @JsonProperty("data")
    StreamInfoData[] data;

    public StreamInfoDataMain() {

    }

    public StreamInfoData[] getData() {
        return data;
    }

    public void setData(StreamInfoData[] data) {
        this.data = data;
    }
}
