package md.gapla.model.dto.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import md.gapla.model.dto.LanguageDto;

@Data
public class CommonQuestionDto {

    private Long commonQuestionId;

    private String questionValue;

    private String answerValue;
    private LanguageDto language;
}
