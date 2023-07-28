package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.input.ExamInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamCourseService {
    
    List<ExamDto> getExamsByCourseId(Long courseId);
    
    
    ExamDto getExam(Long examId);
    
    ExamEntity getExamEntity(Long examId);
    
    ExamDto addExam(ExamInput input);
    
    ExamDto updateExam(ExamInput input);
    
    List<ExamDto> getAll();
    
    void deleteExam(Long examId);
}
