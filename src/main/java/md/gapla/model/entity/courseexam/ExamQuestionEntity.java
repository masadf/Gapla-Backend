package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examquestion", schema = "public")
@Data
public class ExamQuestionEntity {
    @Id
    @Column(name = "examquestionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examQuestionId;

    @Column(name = "value")
    private String value;

    @Column(name = "status")
    private String status;

    @Column(name = "questiontype")
    private String questionType;

    @OneToMany(mappedBy = "examQuestionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExamQuestionAnswerEntity> variants = new ArrayList<>();
    
    @Column(name = "examtaskid")
    private Long examTaskId;
}
