package md.gapla.model.dto.course;

import lombok.Data;
import md.gapla.model.entity.course.CourseEntity;

@Data
public class CourseDetailsDto {
    private Long courseDetailsId;

    private String labelValue;

    private String contentValue;

    private Long courseId;

}
