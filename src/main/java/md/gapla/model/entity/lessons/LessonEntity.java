package md.gapla.model.entity.lessons;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.account.AccountRoleEntity;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.test.TestQuestionEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lessons", schema = "public")
@Data
public class LessonEntity {
    @Id
    @Column(name = "lessonid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid")
    private CourseEntity course;

    @Column(name = "lessonname")
    private String lessonName;

    @Column(name = "lessontext")
    private String lessonText;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "lessonstestquestion", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "lessonid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "testquestionid")
            }
    )
    private List<TestQuestionEntity> questions = new ArrayList<>();

    @OneToMany(mappedBy = "lesson")
    private List<LessonMaterialsEntity> materials=new ArrayList<>();
}
