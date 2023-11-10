package md.gapla.model.dto.course;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.dto.lessons.LessonDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDto;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {
    private Long courseId;

    private String courseName;

    private LevelLanguageDto levelLanguage;
    private LanguageDto language;
    private ObjectStatusEnum status;
    private List<LessonDto> lessons;
//    private List<CourseDetailsDto> details;
    private List<ExamDto> tests = new ArrayList<>();
}
