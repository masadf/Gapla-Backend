package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examtask", schema = "public")
@Data
public class ExamTaskEntity {
    @Id
    @Column(name = "examtaskid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examTaskId;

    @Column(name = "testquestiontypeid")
    private Long testQuestionTypeId;

    @Column(name = "value")
    private String value;

    @Column(name = "audiovalue")
    private String audioValue;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "examtaskexamquestion", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "examtaskid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "examquestionid")
            }
    )
    private List<ExamQuestionEntity> questions = new ArrayList<>();

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "examtasktext", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "examtaskid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "examtextid")
            }
    )
    private List<ExamTextEntity> texts = new ArrayList<>();
    
    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "examexamtask", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "examtaskid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "examid")
            }
    )
    private List<ExamEntity> exams = new ArrayList<>();

}
