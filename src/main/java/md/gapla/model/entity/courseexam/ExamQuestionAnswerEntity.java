package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "examquestionanswer", schema = "public")
@Data
public class ExamQuestionAnswerEntity {
    @Id
    @Column(name = "examquestionanswerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examQuestionAnswerId;

    @Column(name = "examquestionid")
    private Long examQuestionId;

    @Column(name = "value")
    private String value;

    @Column(name = "iscorrect")
    private Integer isCorrect;

}
