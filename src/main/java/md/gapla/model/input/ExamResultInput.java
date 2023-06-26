package md.gapla.model.input;

import lombok.Data;

@Data
public class ExamResultInput {
    private Long courseId;
    private Long examId;

    private String  email;

    private Double reading;
    private Double listening;
    private Double total;

}
