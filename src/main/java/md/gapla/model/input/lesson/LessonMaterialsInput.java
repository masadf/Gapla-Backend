package md.gapla.model.input.lesson;

import lombok.Data;
import md.gapla.model.dto.lessons.LessonDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeDto;

@Data
public class LessonMaterialsInput {
    private Long lessonMaterialId;

    private Long lessonId;

    private Long lessonMaterialsTypeId;
}
