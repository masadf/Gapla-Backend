package md.gapla.model.dto.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestQuestionTypeLanguageDto {
    private Long testQuestionTypeLanguageId;

    private String value;
    private Long testQuestionTypeId;
    private String languageCode;

    private List<TestQuestionTypeDetailDto> details = new ArrayList<>();
}
