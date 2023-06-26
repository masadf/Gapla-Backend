package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.test.TestQuestionEntity;
import md.gapla.repository.exam.ExamQuestionRepository;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examquestiontasktext", schema = "public")
@Data
public class ExamQuestionTaskTextEntity {
    @Id
    @Column(name = "examquestiontasktextid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examQuestionTaskTextId;

    @ManyToOne
    @JoinColumn(name = "examtextid", referencedColumnName = "examtextid")
    private ExamTextEntity examText;

    @Column(name = "examtaskid")
    private Long examTaskId;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "examquestionexams", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "examquestiontasktextid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "examquestionid")
            }
    )
    private List<ExamQuestionEntity> questions = new ArrayList<>();

}
