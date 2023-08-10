package md.gapla.model.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamQuestionInput {
	private Long examQuestionId;
	
	private String value;
	
	private String status;
	
	private String questionType;
	
	private List<Long> variants = new ArrayList<>();
}
