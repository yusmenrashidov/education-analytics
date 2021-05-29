package com.education.analytics.service.data;

import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.model.exception.StudentResultNotFoundException;

import java.util.List;
import java.util.Map;

public interface IStudentResultService {

    List<StudentResult> getAll();

    Map<Double, Integer> getGradesResult();

    Map<String , Double> getAverageGrade();

    List<StudentResult> getResultsByGrade(int grade);
}
