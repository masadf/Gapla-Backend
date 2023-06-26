package md.gapla.model.dto.lessons;

import lombok.Data;
import md.gapla.model.dto.course.CourseShortDto;
import md.gapla.model.dto.test.TestQuestionDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonDto {
    private Long lessonId;
    private String lessonName;
    private String lessonText;
    private CourseShortDto course;

    private List<TestQuestionDto> questions = new ArrayList<>();

    private List<LessonMaterialsDto> materials = new ArrayList<>();
}
