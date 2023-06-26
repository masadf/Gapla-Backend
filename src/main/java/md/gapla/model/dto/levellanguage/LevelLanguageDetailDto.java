package md.gapla.model.dto.levellanguage;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;

@Data
public class LevelLanguageDetailDto {
    private Long levelLanguageDetailId;

    private String labelValue;

    private String contentValue;

    private LanguageDto language;
    private LevelLanguageDto levelLanguage;
}
