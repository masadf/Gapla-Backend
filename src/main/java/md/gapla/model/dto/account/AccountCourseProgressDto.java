package md.gapla.model.dto.account;

import lombok.Data;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.enums.AccountCourseProgressStatusEnum;

@Data
public class AccountCourseProgressDto {
    private Long accountCourseProgressId;

    private CourseDto course;

    private AccountDto account;

    private AccountCourseProgressStatusEnum accountCourseProgressStatusEnum;
}
