package md.gapla.model.input;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.List;

@Data
public class CourseInput {
    private Long courseId;

    private String courseName;

    private Long levelLanguageId;
    private String languageCode;

    private ObjectStatusEnum status;
    private List<Long> lessons;

    private List<Long> tests;
}
