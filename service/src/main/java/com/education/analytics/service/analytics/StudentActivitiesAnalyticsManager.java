package com.education.analytics.service.analytics;

import com.education.analytics.model.domain.Activity;
import com.education.analytics.service.data.IStudentActivitiesService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class StudentActivitiesAnalyticsManager implements IActivitiesAnalyticsManager {

    private static final String VIEWED_LECTURES_FILTER_PREFIX =  "File: Лекция";
    private static final String DATE_TIME_FORMATTER = "dd/MM/yy, HH:mm";
    private static final String VIEWED_FILTER = "viewed";
    private static final String WIKI_FILTER = "Wiki";

    private final IStudentActivitiesService studentActivitiesService;

    public StudentActivitiesAnalyticsManager(IStudentActivitiesService studentActivitiesService) {
        this.studentActivitiesService = studentActivitiesService;
    }

    @Override
    public Map<String, Integer> getAllViewedLectures() {
        Map<String, Integer> targetMap = new HashMap<>();
        targetMap.put("Lectures viewed", filterViewedLectures().size());
        return targetMap;
    }

    @Override
    public Map<String, Integer> getViewedLecturesInTimeFrame(String startTime, String endTime) {
        Map<String, Integer> targetMap = new HashMap<>();


        List<Activity> filteredLecturesByTime =filterViewedLectures().stream()
                .filter(lecture -> isInTimeFrame(lecture, startTime, endTime))
                .collect(Collectors.toList());

        targetMap.put("Viewed lectures", filteredLecturesByTime.size());

        return targetMap;
    }

    @Override
    public Map<Integer, Integer> getViewedWikies() {
        Map<Integer, Integer> targetMap = new HashMap<>();

        for( Integer id: getActivityIds()) {
            targetMap.put(id, getActivitiesById(id.toString()).size());
        }

        return targetMap.entrySet()
                .stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private boolean isInTimeFrame(Activity lecture, String startTime, String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMATTER);
        try {
            return  simpleDateFormat.parse(lecture.getTime()).getTime()  > simpleDateFormat.parse(startTime).getTime() &&
                    simpleDateFormat.parse(lecture.getTime()).getTime()  < simpleDateFormat.parse(endTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Activity> getActivitiesById(String id) {
       return studentActivitiesService.getAll().stream()
                .filter(activity -> activity.getEventContext().startsWith(WIKI_FILTER) && activity.getDescription().contains(VIEWED_FILTER))
                .filter(activity -> extractIdFromDescription(activity.getDescription()).equals(id))
                .collect(Collectors.toList());
    }

    private Set<Integer> getActivityIds(){
        return studentActivitiesService.getAll().stream()
                .filter(activity -> activity.getEventContext().startsWith(WIKI_FILTER) && activity.getDescription().contains(VIEWED_FILTER))
                .map(activity -> Integer.valueOf(extractIdFromDescription(activity.getDescription())))
                .collect(Collectors.toSet());
    }

    private List<Activity> filterViewedLectures(){
        return studentActivitiesService.getAll()
                .stream()
                .filter(courseLog -> courseLog.getEventContext().startsWith(VIEWED_LECTURES_FILTER_PREFIX))
                .collect(Collectors.toList());
    }

    private List<Activity> getWikies(){
        return studentActivitiesService.getAll().stream()
                .filter(activity -> activity.getEventContext().startsWith(WIKI_FILTER) && activity.getDescription().contains(VIEWED_FILTER))
                .collect(Collectors.toList());
    }

    private String extractIdFromDescription(String description){
        return description.substring(description.length() - 6, description.length()-1).substring(0,4);
    }
}
