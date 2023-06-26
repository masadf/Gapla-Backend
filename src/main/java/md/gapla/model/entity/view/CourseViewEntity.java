package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "courseview", schema = "public")
@Data
public class CourseViewEntity {
    @Id
    @Column(name = "courseid")
    private Long courseId;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "status")
    private String status;

    @Column(name = "languageid")
    private Long languageId;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "languagename")
    private String languageName;

    @Column(name = "levellanguageid")
    private String levelLanguageId;
    @Column(name = "levellanguagename")
    private String levelLanguageName;


}
