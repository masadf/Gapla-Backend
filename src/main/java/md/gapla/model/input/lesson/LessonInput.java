package md.gapla.model.input.lesson;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonInput {
    private Long lessonId;

    private Long courseId;
    private ObjectStatusEnum status;

    private List<Long> questions = new ArrayList<>();
}
