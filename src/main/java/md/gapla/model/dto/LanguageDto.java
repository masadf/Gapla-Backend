package md.gapla.model.dto;

import lombok.Data;

@Data
public class LanguageDto {
    private Long languageId;

    private String languageCode;

    private String languageImage;
    private String languageName;
}
