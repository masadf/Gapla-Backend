package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.course.CourseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courseexam", schema = "public")
@Data
public class CourseExamEntity {
    @Id
    @Column(name = "courseexamid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseExamId;

    @Column(name = "examid")
    private Long examId;

    @Column(name = "courseid")
    private Long courseId;


}
