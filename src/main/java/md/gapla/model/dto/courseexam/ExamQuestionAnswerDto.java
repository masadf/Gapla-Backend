package md.gapla.model.dto.courseexam;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class ExamQuestionAnswerDto {
    private Long examQuestionAnswerId;

    private Long examQuestionId;

    private String value;

    private Integer isCorrect;

}
