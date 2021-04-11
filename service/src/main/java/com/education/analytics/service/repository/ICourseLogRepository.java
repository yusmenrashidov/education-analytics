package com.education.analytics.service.repository;

import com.education.analytics.model.domain.CourseLog;

import java.util.List;

public interface ICourseLogRepository {

    List<CourseLog> get();
}
