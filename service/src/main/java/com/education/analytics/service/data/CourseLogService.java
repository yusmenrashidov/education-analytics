package com.education.analytics.service.data;

import com.education.analytics.model.domain.CourseLog;
import com.education.analytics.model.exception.CourseLogServiceException;
import com.education.analytics.service.repository.ICourseLogRepository;

import java.util.List;

public class CourseLogService implements ICourseLogService {

    private final ICourseLogRepository courseLogRepository;

    public CourseLogService(ICourseLogRepository courseLogRepository) {
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
}
