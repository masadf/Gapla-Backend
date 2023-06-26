package md.gapla.model.dto.account;

import lombok.Data;
import md.gapla.model.dto.lessons.LessonDto;

@Data
public class AccountCourseLessonProgressDto {
    private Long accountCourseLessonProgressId;

    private LessonDto lesson;
}
