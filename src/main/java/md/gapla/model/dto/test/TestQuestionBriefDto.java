package md.gapla.model.dto.test;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.List;

@Data
public class TestQuestionBriefDto {
    private Long testQuestionId;
    private String value;
}
