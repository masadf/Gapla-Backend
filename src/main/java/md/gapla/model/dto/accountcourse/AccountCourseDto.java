package md.gapla.model.dto.accountcourse;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AccountCourseDto {

    private Long courseId;
    private String labelValue;

    private String contentValue;

    private String languageCode;

    private String accountCourseProgressStatus;

}
