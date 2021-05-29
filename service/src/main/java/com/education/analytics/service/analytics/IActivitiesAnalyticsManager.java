package com.education.analytics.service.analytics;

import com.education.analytics.model.domain.Activity;

import java.util.List;
import java.util.Map;

public interface IActivitiesAnalyticsManager {

    Map<String, Integer> getAllViewedLectures();
    Map<String, Integer> getViewedLecturesInTimeFrame(String startTime, String endTime);
    Map<Integer, Integer> getViewedWikies();
}
