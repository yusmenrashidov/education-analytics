package com.education.analytics.service.repository;

import com.education.analytics.model.domain.Activity;

import java.util.List;

public interface ICourseLogRepository {

    List<Activity> get();
}
