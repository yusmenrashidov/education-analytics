package com.education.analytics.data.api.spring.controller;

import com.education.analytics.model.domain.StudentCourseActivity;
import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.service.analytics.IStudentResultAnalyticsManager;
import com.education.analytics.service.data.IStudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class StudentsResultApiController {

    private final IStudentResultAnalyticsManager studentResultAnalyticsManager;

    @Autowired
    public StudentsResultApiController(IStudentResultAnalyticsManager studentResultAnalyticsManager) {
        this.studentResultAnalyticsManager = studentResultAnalyticsManager;
    }

    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/grade",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<Double, Integer>> getByGrade()
    {
        return ok().body(studentResultAnalyticsManager.getGradesByCount());
    }

    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/grade/average",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Double> getAverageGrade()
    {
        return ok().body(studentResultAnalyticsManager.getAverageGrade());
    }

    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/scholarship",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<List<StudentResult>> getScholarshipStudents()
    {
        return ok().body(studentResultAnalyticsManager.getEligibleStudentsForScholarship());
    }

    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/activity",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<List<StudentCourseActivity>> getStudentResultByViewedLectures()
    {
        return ok().body(studentResultAnalyticsManager.getStudentsToViewedLectures());
    }

    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/grade/median",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Double> getMedianStudentGrades()
    {
        return ok().body(studentResultAnalyticsManager.getMedianGrade());
    }
}
