package md.gapla.model.input.test;

import lombok.Data;
import md.gapla.model.dto.test.TestQuestionDto;
import md.gapla.model.dto.test.TestTextDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TestInput {
    private Long testId;

    private String testName;
    private Long testTimeTypeId;
    private String languageCode;

    private String testText;

    private List<Long> questions = new ArrayList<>();
}
