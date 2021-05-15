package com.education.analytics.service.data;

import com.education.analytics.model.domain.CourseLog;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IStudentActivitiesService {

    List<CourseLog> getAll();

    List<CourseLog> getAllByComponent(String component);

    Map<String, Integer> getAllViewedLectures();

    Set<String> get();
}
