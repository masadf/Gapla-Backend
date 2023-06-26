package md.gapla.model.input;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDto;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.List;

@Data
public class CourseInput {
    private Long courseId;

    private String courseName;

    private Long levelLanguageId;
    private String languageCode;

    private ObjectStatusEnum status;

    private List<Long> tests;
}
