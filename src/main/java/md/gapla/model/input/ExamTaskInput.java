package md.gapla.model.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamTaskInput {
	private Long examTaskId;
	
	private Long testQuestionTypeId;
	
	private String value;
	
	private String audioValue;
	
	private List<Long> questions = new ArrayList<>();
	
	private List<Long> texts = new ArrayList<>();
	
	private Long examId;
}
