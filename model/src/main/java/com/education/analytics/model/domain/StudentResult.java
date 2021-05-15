package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class StudentResult {

    private final Double id;
    private final Double result;

    @JsonCreator
    public StudentResult( //
       @JsonProperty("id") Double id, //
       @JsonProperty("result") Double result //
    ) {
        this.id = id;
        this.result = result;
    }


    @JsonProperty("id")
    public Double getId() {
        return id;
    }

    @JsonProperty("result")
    public Double getResult() {
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
