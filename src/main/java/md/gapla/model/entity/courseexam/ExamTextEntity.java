package md.gapla.model.entity.courseexam;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "examtext", schema = "public")
@Data
public class ExamTextEntity {
    @Id
    @Column(name = "examtextid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examTextId;

    @Column(name = "value")
    private String value;

}
