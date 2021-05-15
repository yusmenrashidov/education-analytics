package com.education.analytics.service.data;

import com.education.analytics.model.domain.CourseLog;
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
    public List<CourseLog> getAll() {
        try {
            return courseLogRepository.get();
        } catch (Exception e) {
            throw new CourseLogServiceException();
        }
    }

    @Override
    public List<CourseLog> getAllByComponent(String component) {
        return courseLogRepository.get()
                .stream()
                .filter(courseLog -> courseLog.getComponent().equals(component))
                .collect(Collectors.toList());

    }

    @Override
    public Map<String, Integer> getAllViewedLectures() {
        int viewedCourses = (int) courseLogRepository.get()
                .stream()
                .filter(courseLog -> courseLog.getEventContext().startsWith("File: Лекция"))
                .count();

        Map<String, Integer> result =  new HashMap<>();
        result.put("Lectures viewed", viewedCourses);

        return result;
    }

    @Override
    public Set<String> get(){
        return courseLogRepository.get().stream()
                .filter(courseLog -> courseLog.getEventContext().startsWith("Wiki"))
                .map(courseLog -> courseLog.getEventContext())
                .collect(Collectors.toSet());
    }

}
