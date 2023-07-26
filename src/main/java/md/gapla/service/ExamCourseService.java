package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.input.ExamInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamCourseService {
    
    List<ExamDto> getExamsByCourseId(Long courseId);
    
    Page<ExamDto> getExamPage(PageParamDto pageParamDto);
    
    ExamDto getExam(Long examId);
    
    ExamEntity getExamEntity(Long examId);
    
    ExamDto addExam(ExamInput input);
    
    ExamDto updateExam(ExamInput input);
    
    List<ExamEntity> findAll();
    
    void deleteExam(Long examId);
}
