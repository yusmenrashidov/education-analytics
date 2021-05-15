package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class StudentResult {

    private final String id;
    private final Integer result;

    @JsonCreator
    public StudentResult( //
       @JsonProperty("id") String id, //
       @JsonProperty("result") Integer result //
    ) {
        this.id = id;
        this.result = result;
    }


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public Integer getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentResult that = (StudentResult) o;
        return result == that.result &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result);
    }
}
