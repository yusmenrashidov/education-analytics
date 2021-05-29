package com.education.analytics.service.data;

import com.education.analytics.model.domain.Activity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IStudentActivitiesService {

    List<Activity> getAll();

    List<Activity> getAllByComponent(String component);

    List<Activity> getAllViewedLectures();

    Set<String> get();
}
