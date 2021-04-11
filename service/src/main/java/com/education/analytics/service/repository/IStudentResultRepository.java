package com.education.analytics.service.repository;

import com.education.analytics.model.domain.StudentResult;

import java.util.List;

public interface IStudentResultRepository {

    List<StudentResult> get();
}
