package md.gapla.model.dto.test;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.List;

@Data
public class TestQuestionDto {
    private Long testQuestionId;

    private Long testQuestionTypeId;
    private String testQuestionTypeValue;
    private String questionType;

    private String value;
    private String audioValue;
    private ObjectStatusEnum status;

    private List<TestAnswerDto> variants;
}
