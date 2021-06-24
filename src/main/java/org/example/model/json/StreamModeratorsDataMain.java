package org.example.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamModeratorsDataMain {


    @JsonProperty("data")
    StreamModeratorsData[] data;

    public StreamModeratorsDataMain(){

    }

    public StreamModeratorsData[] getData() {
        return data;
    }

    public void setData(StreamModeratorsData[] data) {
        this.data = data;
    }


}
