package md.gapla.model.entity.course;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "coursedetails", schema = "public")
@Data
public class CourseDetailsEntity {
    @Id
    @Column(name = "coursedetailsid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseDetailsId;

    @Column(name = "labelvalue")
    private String labelValue;

    @Column(name = "contentvalue")
    private String contentValue;

    @Column(name = "courseid")
    private Long courseId;

}
