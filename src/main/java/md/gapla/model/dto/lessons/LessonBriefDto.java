package md.gapla.model.dto.lessons;

import lombok.Data;
import md.gapla.model.dto.course.CourseShortDto;
import md.gapla.model.dto.test.TestQuestionBriefDto;
import md.gapla.model.dto.test.TestQuestionDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonBriefDto {
    private Long lessonId;

    private CourseShortDto course;

    private List<TestQuestionBriefDto> questions = new ArrayList<>();

    private List<LessonMaterialsDto> materials=new ArrayList<>();
}
