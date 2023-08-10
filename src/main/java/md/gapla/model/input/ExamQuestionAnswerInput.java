package md.gapla.model.input;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ExamQuestionAnswerInput {
	private Long examQuestionAnswerId;
	
	private String value;
	
	private Integer isCorrect;
}
