package md.gapla.model.dto.common;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;

@Data
public class CommonInfoTypeLanguageDto {
    private Long commonInfoLanguageId;

    private Long commonInfoTypeId;

    private String value;

    private LanguageDto language;
}
