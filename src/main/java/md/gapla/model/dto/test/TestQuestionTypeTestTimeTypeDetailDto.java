package md.gapla.model.dto.test;

import lombok.Data;

import java.util.List;

@Data
public class TestQuestionTypeTestTimeTypeDetailDto {

    private String timeTypeValue;
    private String typeQuestionValue;
    private List<String> details;
}
