package md.gapla.model.input;

import lombok.Data;

import java.util.List;

@Data
public class ExamInput {
	private Long examId;
	
	private String examName;
	
	private List<Long> examTasksToAdd;
	private List<Long> examTasksToDelete;
	
	private List<Long> coursesToAdd;
	private List<Long> coursesToDelete;
}
