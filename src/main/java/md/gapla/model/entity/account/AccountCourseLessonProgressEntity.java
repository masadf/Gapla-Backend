package md.gapla.model.entity.account;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.lessons.LessonEntity;

@Entity
@Table(name ="accountcourselessonproggres" ,schema = "public")
@Data
public class AccountCourseLessonProgressEntity {
    @Id
    @Column(name = "accountcourselessonproggresid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountCourseLessonProgressId;

    @ManyToOne
    @JoinColumn(name = "lessonid", referencedColumnName = "lessonid")
    private LessonEntity lesson;
}
