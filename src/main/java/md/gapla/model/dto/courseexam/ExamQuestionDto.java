package md.gapla.model.dto.courseexam;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamQuestionDto {
    private Long examQuestionId;

    private String value;

    private String status;

    private String questionType;
    private List<ExamQuestionAnswerDto> variants = new ArrayList<>();
}
