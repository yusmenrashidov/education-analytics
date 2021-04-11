package com.education.analytics.service.data;

import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.model.exception.StudentResultNotFoundException;

import java.util.List;

public interface IStudentResultService {

    StudentResult get(String id) throws StudentResultNotFoundException;

    List<StudentResult> getAll();
}
