package md.gapla.service;

import md.gapla.model.dto.courseexam.ExamQuestionDto;
import md.gapla.model.entity.courseexam.ExamQuestionEntity;
import md.gapla.model.input.ExamQuestionInput;

import java.util.List;

public interface ExamQuestionService {
	List<ExamQuestionDto> getAll();
	
	ExamQuestionDto getExamQuestion(Long examQuestionId);
	
	ExamQuestionEntity getExamQuestionEntity(Long examQuestionId);
	
	ExamQuestionDto addExamQuestion(ExamQuestionInput input);
	
	ExamQuestionDto updateExamQuestion(ExamQuestionInput input);
	
	void deleteExamQuestion(Long examQuestionId);
}
