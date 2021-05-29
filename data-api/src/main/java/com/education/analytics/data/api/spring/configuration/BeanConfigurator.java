package com.education.analytics.data.api.spring.configuration;

import com.education.analytics.service.analytics.IActivitiesAnalyticsManager;
import com.education.analytics.service.analytics.IStudentResultAnalyticsManager;
import com.education.analytics.service.analytics.StudentActivitiesAnalyticsManager;
import com.education.analytics.service.analytics.StudentResultAnalyticsManager;
import com.education.analytics.service.data.StudentActivitiesService;
import com.education.analytics.service.data.IStudentActivitiesService;
import com.education.analytics.service.data.IStudentResultService;
import com.education.analytics.service.data.StudentResultService;
import com.education.analytics.service.repository.CourseLogExcelExtractor;
import com.education.analytics.service.repository.ICourseLogRepository;
import com.education.analytics.service.repository.IStudentResultRepository;
import com.education.analytics.service.repository.StudentResultExcelExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurator {

    //TODO: Add source paths as env variables
    private static final String DATA_COURSE_LOGS_SOURCE_PATH = "D:/TU/Semester 6/PTS/InputData/Logs_Course A_StudentsActivities.xlsx";
    private static final String DATA_STUDENT_RESULTS_SOURCE_PATH = "D:/TU/Semester 6/PTS/InputData/Course A_StudentsResults_Year 1.xlsx";

    @Bean
    public ICourseLogRepository courseLogRepository() {
        return new CourseLogExcelExtractor(DATA_COURSE_LOGS_SOURCE_PATH);
    }

    @Bean
    public IStudentResultRepository studentResultRepository() {
        return new StudentResultExcelExtractor(DATA_STUDENT_RESULTS_SOURCE_PATH);
    }

    @Bean
    public IStudentActivitiesService courseLogService(ICourseLogRepository courseLogRepository) {
        return new StudentActivitiesService(courseLogRepository);
    }

    @Bean
    public IStudentResultService studentResultService(IStudentResultRepository studentResultRepository) {
        return new StudentResultService(studentResultRepository);
    }

    @Bean
    public IStudentResultAnalyticsManager studentResultAnalyticsManager(IStudentResultService studentResultService, IStudentActivitiesService studentActivitiesService) {
        return new StudentResultAnalyticsManager(studentResultService, studentActivitiesService);
    }

    @Bean
    IActivitiesAnalyticsManager activitiesAnalyticsManager(IStudentActivitiesService studentActivitiesService){
        return new StudentActivitiesAnalyticsManager(studentActivitiesService);
    }
}
