package md.gapla.model.entity.account;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.course.CourseEntity;

@Entity
@Table(name = "accountcourseproggres", schema = "public")
@Data
public class AccountCourseProgressEntity {
    @Id
    @Column(name = "accountcourseproggresid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountCourseProgressId;

    @Column(name = "courseid")
    private Long courseId;

    @Column(name = "accountid")
    private Long accountId;

    @Column(name = "accountcourseproggresstatus")
    private String accountCourseProgressStatus;
}
