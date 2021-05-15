package com.education.analytics.data.api.spring.controller;

import com.education.analytics.model.domain.CourseLog;
import com.education.analytics.service.data.IStudentActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class StudentActivitiesApiController {

    private final IStudentActivitiesService courseLogService;

    @Autowired
    public StudentActivitiesApiController(IStudentActivitiesService courseLogService) {
        this.courseLogService = courseLogService;
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-activities/component", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Collection<CourseLog>> get(@RequestParam(required = true, name ="component") String component) {
        return ok().body(courseLogService.getAllByComponent(component));
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-activities/viewed-lectures", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<String, Integer>> getAllViewedLectures() {
        return ok().body(courseLogService.getAllViewedLectures());
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-activities/test", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Set<String>> get() {
        return ok().body(courseLogService.get());
    }
}
