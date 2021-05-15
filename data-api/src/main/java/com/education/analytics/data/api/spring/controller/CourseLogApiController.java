package com.education.analytics.data.api.spring.controller;

import com.education.analytics.model.domain.CourseLog;
import com.education.analytics.service.data.ICourseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class CourseLogApiController {

    private final ICourseLogService courseLogService;

    @Autowired
    public CourseLogApiController(ICourseLogService courseLogService) {
        this.courseLogService = courseLogService;
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/course-logs", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Collection<CourseLog>> getAll() {
        return ok().body(courseLogService.getAll());
    }
}
