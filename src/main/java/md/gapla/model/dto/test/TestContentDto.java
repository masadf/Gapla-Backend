package md.gapla.model.dto.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TestContentDto {
    private Long testId;

    private String testName;

    private String testText;

    private List<TestQuestionDto> reading = new ArrayList<>();
    private List<TestQuestionDto> listening = new ArrayList<>();
}
