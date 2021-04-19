package com.education.analytics.data.api.spring.configuration;

import com.education.analytics.service.data.CourseLogService;
import com.education.analytics.service.data.ICourseLogService;
import com.education.analytics.service.data.IStudentResultService;
import com.education.analytics.service.data.StudentResultService;
import com.education.analytics.service.repository.CourseLogExcelExtractor;
import com.education.analytics.service.repository.ICourseLogRepository;
import com.education.analytics.service.repository.IStudentResultRepository;
import com.education.analytics.service.repository.StudentResultExcelExtractor;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Value;
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
    public ICourseLogService courseLogService(ICourseLogRepository courseLogRepository) {
        return new CourseLogService(courseLogRepository);
    }

    @Bean
    public IStudentResultService studentResultService(IStudentResultRepository studentResultRepository) {
        return new StudentResultService(studentResultRepository);
    }
}
