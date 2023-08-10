package md.gapla.service;

import md.gapla.model.dto.courseexam.ExamQuestionAnswerDto;
import md.gapla.model.entity.courseexam.ExamQuestionAnswerEntity;
import md.gapla.model.input.ExamQuestionAnswerInput;

import java.util.List;

public interface ExamQuestionAnswerService {
	List<ExamQuestionAnswerDto> getAll();
	
	ExamQuestionAnswerDto getExamQuestionAnswer(Long examQuestionAnswerId);
	
	ExamQuestionAnswerEntity getExamQuestionAnswerEntity(Long examQuestionAnswerId);
	
	ExamQuestionAnswerDto addExamQuestionAnswer(ExamQuestionAnswerDto input);
	
	ExamQuestionAnswerDto updateExamQuestionAnswer(ExamQuestionAnswerInput input);
	
	void deleteExamQuestionAnswer(Long examQuestionAnswerId);
}
