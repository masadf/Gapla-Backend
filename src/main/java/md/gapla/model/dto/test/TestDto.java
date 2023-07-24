package md.gapla.model.dto.test;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class TestDto {
    private Long testId;

    private String testName;

    private TestTextDto testText;

    private Long testTimeTypeId; //Id to get Time for test;
    
    private LocalDateTime creationDate; //DateTime of creation

    private Set<TestQuestionDto> questions = new HashSet<>();
    
    private Integer questionsQuantity;
}
