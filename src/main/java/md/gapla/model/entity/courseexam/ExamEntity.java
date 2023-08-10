package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.test.TestEntity;
import md.gapla.model.entity.test.TestQuestionEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam", schema = "public")
@Data
public class ExamEntity {
    @Id
    @Column(name = "examid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @Column(name = "examname")
    private String examName;
    
    @Column(name = "status")
    private ObjectStatusEnum status;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "examId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExamTaskEntity> tasks = new ArrayList<>();
    
    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "courseexam", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "examid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "courseid")
            }
    )
    private List<CourseEntity> courses = new ArrayList<>();
    
    @Column(name = "creationdate")
    private LocalDateTime creationDate;

}
