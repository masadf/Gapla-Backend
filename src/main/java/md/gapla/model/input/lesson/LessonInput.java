package md.gapla.model.input.lesson;

import lombok.Data;
import md.gapla.model.dto.course.CourseShortDto;
import md.gapla.model.dto.lessons.LessonMaterialsDto;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonInput {
    private Long lessonId;
    private String lessonName;
    private String lessonText;
    private String videoLink;
    private Long courseId;
    private ObjectStatusEnum status;

    private List<Long> questions = new ArrayList<>();
    
    private List<Long> materials = new ArrayList<>();
}
