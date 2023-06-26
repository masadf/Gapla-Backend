package md.gapla.model.dto.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TestDetailDto {
    private Long testId;

    private String testName;

    private TestTextDto testText;

    private List<TestQuestionDto> listening = new ArrayList<>();
    private List<TestQuestionDto> reading = new ArrayList<>();
}
