package com.education.analytics.data.api.spring.controller;

import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.service.data.IStudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class StudentsResultApiController {

    private final IStudentResultService studentResultService;

    @Autowired
    public StudentsResultApiController(IStudentResultService studentResultService) {
        this.studentResultService = studentResultService;
    }

    @ResponseBody
    @GetMapping( //
            path = "/data/api/v1/student-results", //
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Collection<StudentResult>> getAll() {
        return ok().body(studentResultService.getAll());
    }


    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/grade",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<Double, Integer>> getByGrade()
    {
        return ok().body(studentResultService.getGradesResult());
    }

    @ResponseBody
    @GetMapping( //
            path = "data/api/v1/student-results/grade/average",
            produces = APPLICATION_JSON_VALUE //
    )
    public ResponseEntity<Map<String, Double>> getAverageGrade()
    {
        return ok().body(studentResultService.getAverageGrade());
    }
}
