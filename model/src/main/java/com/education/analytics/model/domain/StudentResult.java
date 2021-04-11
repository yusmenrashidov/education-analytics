package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentResult {

    private final String id;
    private final int result;

    @JsonCreator
    public StudentResult( //
       @JsonProperty("id") String id, //
       @JsonProperty("result") int result //
    ) {
        this.id = id;
        this.result = result;
    }


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public int getResult() {
        return result;
    }
}
