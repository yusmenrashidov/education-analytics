package com.education.analytics.service.data;

import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.model.exception.StudentResultNotFoundException;
import com.education.analytics.model.exception.StudentResultServiceException;
import com.education.analytics.service.repository.IStudentResultRepository;

import java.util.List;

public class StudentResultService implements IStudentResultService {

    private final IStudentResultRepository studentResultRepository;

    public StudentResultService(IStudentResultRepository studentResultRepository) {
        this.studentResultRepository = studentResultRepository;
    }

    @Override
    public StudentResult get(String id) {
        for (StudentResult studentResult : studentResultRepository.get()) {
            if (studentResult.getId().equals(id)) {
                return studentResult;
            }
        }
        throw new StudentResultNotFoundException();
    }

    @Override
    public List<StudentResult> getAll() {
        try {
            return studentResultRepository.get();
        } catch (Exception e) {
            throw new StudentResultServiceException();
        }
    }
}
