package com.education.analytics.service.data;

import com.education.analytics.model.domain.StudentResult;

import java.util.List;

public interface IStudentResultService {

    StudentResult get(String id);

    List<StudentResult> getAll();
}
