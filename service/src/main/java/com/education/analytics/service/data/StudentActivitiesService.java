package com.education.analytics.service.data;

import com.education.analytics.model.domain.Activity;
import com.education.analytics.model.exception.CourseLogServiceException;
import com.education.analytics.service.repository.ICourseLogRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentActivitiesService implements IStudentActivitiesService {

    private final ICourseLogRepository courseLogRepository;

    public StudentActivitiesService(ICourseLogRepository courseLogRepository) {
        this.courseLogRepository = courseLogRepository;
    }

    @Override
    public List<Activity> getAll() {
        try {
            return courseLogRepository.get();
        } catch (Exception e) {
            throw new CourseLogServiceException();
        }
    }

    @Override
    public List<Activity> getAllByComponent(String component) {
        return courseLogRepository.get()
                .stream()
                .filter(courseLog -> courseLog.getComponent().equals(component))
                .collect(Collectors.toList());

    }

    @Override
    public List<Activity> getAllViewedLectures() {
        return courseLogRepository.get()
                .stream()
                .filter(courseLog -> courseLog.getEventContext().startsWith("File: Лекция"))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> get(){
        return courseLogRepository.get().stream()
                .filter(courseLog -> courseLog.getEventContext().startsWith("Wiki"))
                .map(courseLog -> courseLog.getEventContext())
                .collect(Collectors.toSet());
    }

}
