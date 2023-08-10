package md.gapla.service;

import md.gapla.model.dto.courseexam.ExamTaskDto;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.input.ExamTaskInput;

import java.util.List;

public interface ExamTaskService {
	List<ExamTaskDto> getAll();
	
	ExamTaskDto getExamTask(Long examTaskId);
	
	ExamTaskEntity getExamTaskEntity(Long examTaskId);
	
	ExamTaskDto addExamTask(ExamTaskInput input);
	
	ExamTaskDto updateExamTask(ExamTaskInput input);
	
	void deleteExamTask(Long examTaskId);
}
