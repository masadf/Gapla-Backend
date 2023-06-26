package md.gapla.model.input.test;

import lombok.Data;
import md.gapla.model.dto.test.TestQuestionTypeDetailDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestQuestionTypeLanguageInput {
    private String value;
    private String languageCode;

    private List<TestQuestionTypeDetailDto> details=new ArrayList<>();

}
