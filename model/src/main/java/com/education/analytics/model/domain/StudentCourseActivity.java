package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class StudentCourseActivity {

    private final StudentResult student;
    private final Integer viewedLectures;

    @JsonCreator
    public StudentCourseActivity( //
            @JsonProperty("student") StudentResult studentResult, //
            @JsonProperty("viewedLectures") Integer viewedLectures //
    ){
        this.student = studentResult;
        this.viewedLectures = viewedLectures;
    }

    @JsonProperty("student")
    public StudentResult getStudent() {
        return student;
    }

    @JsonProperty("viewedLectures")
    public Integer getViewedLectures() {
        return viewedLectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseActivity that = (StudentCourseActivity) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(viewedLectures, that.viewedLectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, viewedLectures);
    }
}
