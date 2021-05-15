package com.education.analytics.service.data;

import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.model.exception.StudentResultNotFoundException;

import java.util.List;
import java.util.Map;

public interface IStudentResultService {

    StudentResult getOne(String id) throws StudentResultNotFoundException;

    List<StudentResult> getAll();

    List<StudentResult> getAllById(String id);

    Map<Double, Integer> getGradesResult();

    Map<String , Double> getAverageGrade();
}
