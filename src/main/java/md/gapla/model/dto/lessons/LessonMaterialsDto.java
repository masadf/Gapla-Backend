package md.gapla.model.dto.lessons;

import lombok.Data;
import md.gapla.model.enums.DocumentTypeEnum;

@Data
public class LessonMaterialsDto {
    private Long lessonMaterialId;

    private Long lessonId;

//    private String urlDocument;

    private String documentName;

    private LessonMaterialsTypeDto lessonMaterialsType;
}
