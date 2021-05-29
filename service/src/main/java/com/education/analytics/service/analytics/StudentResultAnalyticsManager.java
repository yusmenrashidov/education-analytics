package com.education.analytics.service.analytics;

import com.education.analytics.model.domain.Activity;
import com.education.analytics.model.domain.StudentCourseActivity;
import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.service.data.IStudentActivitiesService;
import com.education.analytics.service.data.IStudentResultService;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import org.apache.commons.math3.*;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class StudentResultAnalyticsManager implements IStudentResultAnalyticsManager {

    private Set<Double> GRADES = new HashSet<Double>(Arrays.asList(2.0, 3.0, 4.0, 5.0, 6.0));
    private Double SCHOLARSHIP_BOUNDARY = 5.0;

    private final IStudentResultService studentResultService;
    private final IStudentActivitiesService studentActivitiesService;

    public StudentResultAnalyticsManager(IStudentResultService studentResultService,
                                         IStudentActivitiesService studentActivitiesService
    ) {
        this.studentResultService = studentResultService;
        this.studentActivitiesService = studentActivitiesService;
    }

    @Override
    public Map<Double, Integer> getGradesByCount() {
        Map<Double, Integer> targetMap = new HashMap<>();

        GRADES.forEach(grade -> {
            targetMap.put(grade, studentResultService.getResultsByGrade(grade.intValue()).size());
        });
        return targetMap;
    }

    @Override
    public Double getAverageGrade() {
        List<StudentResult> allResults = studentResultService.getAll();

        return allResults.stream()
                .mapToDouble(StudentResult::getResult)
                .average().orElse(0);
    }

    @Override
    public List<StudentResult> getEligibleStudentsForScholarship() {
        List<StudentResult> allResults = studentResultService.getAll();

        return allResults.stream()
                .filter(studentResult -> studentResult.getResult() > SCHOLARSHIP_BOUNDARY)
                .collect(toList());
    }

    @Override
    public List<StudentCourseActivity> getStudentsToViewedLectures() {
        List<StudentResult> allStudents = studentResultService.getAll();
        List<Activity> activities = studentActivitiesService.getAllViewedLectures();

        return allStudents.stream()
                .map(studentResult -> new StudentCourseActivity( //
                        studentResult, //
                        getActivitiesByUser(studentResult.getId().intValue(), activities).size() //
                        )).collect(Collectors.toList());

    }

    @Override
    public Double getMedianGrade() {
        List<Double> grades = studentResultService.getAll().stream().map(StudentResult::getResult).collect(toList());
        Median median = new Median();

        return median.evaluate(grades.stream()
                .mapToDouble((Double value) -> value)
                .toArray()
        );
    }

    private List<Activity> getActivitiesByUser(Integer userId, List<Activity> activities){
        return activities.stream()
                .filter(activity -> {
                    String temp= extractUserIdFromDescription(activity.getDescription());
                    String id = userId.toString();
                        return temp.equals(id);
                    }
                )
                .collect(toList());
    }

    private String extractUserIdFromDescription(String description) {
        return description.substring(18,22);
    }


}
