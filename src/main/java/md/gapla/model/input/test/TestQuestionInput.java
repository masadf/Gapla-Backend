package md.gapla.model.input.test;

import lombok.Data;
import md.gapla.model.dto.test.TestAnswerDto;

import java.util.List;

@Data
public class TestQuestionInput {
    private Long testQuestionId;

    private String languageCode;
    private String questionType;

    private String audioValue;
    private String value;

    private List<TestAnswerDto> variants;
}
