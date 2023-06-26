package md.gapla.model.dto.test;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TestDto {
    private Long testId;

    private String testName;

    private TestTextDto testText;

    private Long testTimeTypeId;

    private Set<TestQuestionDto> questions = new HashSet<>();
}
