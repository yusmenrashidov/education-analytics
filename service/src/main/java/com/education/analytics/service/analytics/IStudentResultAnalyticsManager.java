package com.education.analytics.service.analytics;

import com.education.analytics.model.domain.StudentCourseActivity;
import com.education.analytics.model.domain.StudentResult;

import java.util.List;
import java.util.Map;

public interface IStudentResultAnalyticsManager {

    Map<Double , Integer> getGradesByCount();

    Double getAverageGrade();

    List<StudentResult> getEligibleStudentsForScholarship();

    List<StudentCourseActivity> getStudentsToViewedLectures();

    Double getMedianGrade();
}
