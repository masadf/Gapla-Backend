package md.gapla.model.entity.course;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.test.TestQuestionTypeLanguageEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courselanguage", schema = "public")
@Data
public class CourseLanguageEntity {
    @Id
    @Column(name = "courselanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseLanguageId;

    @Column(name = "labelvalue")
    private String labelValue;

    @Column(name = "contentvalue")
    private String contentValue;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    LanguageEntity language;

    @Column(name = "courseid")
    private Long courseId;

}
