package com.education.analytics.service.data;

import com.education.analytics.model.domain.StudentResult;
import com.education.analytics.model.exception.StudentResultNotFoundException;
import com.education.analytics.model.exception.StudentResultServiceException;
import com.education.analytics.service.repository.IStudentResultRepository;
import org.apache.commons.codec.binary.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentResultService implements IStudentResultService {

    private final IStudentResultRepository studentResultRepository;

    public StudentResultService(IStudentResultRepository studentResultRepository) {
        this.studentResultRepository = studentResultRepository;
    }

    @Override
    public StudentResult getOne(String id) {
        for (StudentResult studentResult : studentResultRepository.get()) {
            if (id.equals(studentResult.getId())) {
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
            e.printStackTrace();
            throw new StudentResultServiceException();
        }
    }

    @Override
    public List<StudentResult> getAllById(String id) {
        try {
            return studentResultRepository.get()
                    .stream()
                        .filter(studentResult -> StringUtils.equals(String.valueOf(studentResult.getId()), id))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            throw new StudentResultServiceException();
        }
    }

    @Override
    public Map<Double, Integer> getGradesResult() {
        Map<Double, Integer> result = new HashMap<>();

        for(Integer grade: getAllGrades()) {
            result.put(grade.doubleValue(), getGradeCount(grade).size());
        }

        return result;
    }

    @Override
    public Map<String, Double> getAverageGrade() {
        Map<Double, Integer> target = getGradesResult();
        Map<String, Double> resultMap = new HashMap<>();

        double result = 0F;
        int total = 0;

        for (Map.Entry<Double, Integer> entry : target.entrySet()) {
            result = result + entry.getKey() * entry.getValue();
            total+=entry.getValue();
        }
        resultMap.put("Average grade", result/total);

        return resultMap;
    }

    private Set<Integer> getAllGrades(){
       return studentResultRepository.get()
               .stream()
               .map(studentResult ->studentResult.getResult().intValue())
               .collect(Collectors.toSet());
   }

   private List<Integer> getGradeCount(Integer grade) {
       return studentResultRepository.get()
               .stream()
               .filter(studentResult -> grade.compareTo(studentResult.getResult().intValue()) == 0)
               .map(studentResult -> studentResult.getResult().intValue())
               .collect(Collectors.toList());
   }
}
