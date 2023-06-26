package md.gapla.model.dto.view;

import lombok.Data;

@Data
public class CourseViewDto {
    private Long courseId;

    private String courseName;

    private String status;

    private Long languageId;

    private String languageCode;

    private String languageName;

    private String levelLanguageId;
    private String levelLanguageName;
}
