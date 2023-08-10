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
    
    @OneToMany(mappedBy = "examTaskId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExamQuestionEntity> questions = new ArrayList<>();
    
    @OneToMany(mappedBy = "examTaskId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExamTextEntity> texts = new ArrayList<>();
    
    @Column(name = "examid")
    private Long examId;
}
