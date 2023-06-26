package md.gapla.model.entity.course;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.test.TestEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course", schema = "public")
@Data
public class CourseEntity {
    @Id
    @Column(name = "courseid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "coursename")
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "levellanguageid", referencedColumnName = "levellanguageid")
    private LevelLanguageEntity levelLanguage;
    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "courseexam", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "courseid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "examid")
            }
    )
    private List<ExamEntity> tests = new ArrayList<>();
}
