package md.gapla.model.dto.test;

import lombok.Data;

@Data
public class TestAnswerDto {
    private Long testAnswerId;

    private String value;

    private Integer isCorrect;

}
