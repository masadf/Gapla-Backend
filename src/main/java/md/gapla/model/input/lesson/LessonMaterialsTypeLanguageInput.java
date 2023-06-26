package md.gapla.model.input.lesson;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeDto;

@Data
public class LessonMaterialsTypeLanguageInput {

    private String value;

    private Long lessonMaterialsTypeId;

    private String languageCode;
}
