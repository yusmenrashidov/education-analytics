package com.education.analytics.data.api.spring.controller;

import com.education.analytics.model.domain.Activity;
import com.education.analytics.service.analytics.IActivitiesAnalyticsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class StudentActivitiesApiController {

    private final IActivitiesAnalyticsManager activitiesAnalyticsManager;

    @Autowired
    public StudentActivitiesApiController(IActivitiesAnalyticsManager activitiesAnalyticsManager) {
        this.activitiesAnalyticsManager = activitiesAnalyticsManager;
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-activities/viewed-lectures", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<String, Integer>> getAllViewedLectures() {
        return ok().body(activitiesAnalyticsManager.getAllViewedLectures());
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-activities/viewed-lectures/date", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<String, Integer>> getAllViewedLecturesByDate( //
            @RequestParam(name = "startDate", required = true) String startDate, //
            @RequestParam(name = "endDate", required = true) String endDate //
    ) {
        return ok().body(activitiesAnalyticsManager.getViewedLecturesInTimeFrame(startDate, endDate));
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-activities/wiki", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<Integer, Integer>> getAllViewedLecturesByDate() {
        return ok().body(activitiesAnalyticsManager.getViewedWikies());
    }

}
