package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "testanswer", schema = "public")
@Data
public class TestAnswerEntity {
    @Id
    @Column(name = "testanswerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testAnswerId;

    @Column(name = "testquestionid")
    private Long testQuestionId;

    @Column(name = "value")
    private String value;

    @Column(name = "iscorrect")
    private Integer isCorrect;

}
