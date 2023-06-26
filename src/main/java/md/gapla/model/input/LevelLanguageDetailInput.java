package md.gapla.model.input;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDto;

@Data
public class LevelLanguageDetailInput {
    private Long levelLanguageDetailId;

    private String labelValue;

    private String contentValue;

    private String languageCode;
    private Long levelLanguageId;
}
