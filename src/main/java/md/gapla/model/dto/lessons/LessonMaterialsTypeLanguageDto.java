package md.gapla.model.dto.lessons;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;

@Data
public class LessonMaterialsTypeLanguageDto {
    private Long lessonMaterialsTypeLanguageId;

    private String value;

    private Long lessonMaterialsTypeId;

    private String languageCode;
}
