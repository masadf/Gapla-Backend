package md.gapla.model.dto.course;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDto;

@Data
public class CourseShortDto {
    private Long courseId;

    private String courseName;
}
