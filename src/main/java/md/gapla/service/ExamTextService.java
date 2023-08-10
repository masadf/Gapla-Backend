package md.gapla.service;


import md.gapla.model.dto.courseexam.ExamTextDto;
import md.gapla.model.entity.courseexam.ExamTextEntity;

import java.util.List;

public interface ExamTextService {
	List<ExamTextDto> getAll();
	
	ExamTextDto getExamText(Long examTextId);
	
	ExamTextEntity getExamTextEntity(Long examTextId);
	
	ExamTextDto addExamText(ExamTextDto input);
	
	ExamTextDto updateExamText(ExamTextDto input);
	
	void deleteExamText(Long examTextId);
}
