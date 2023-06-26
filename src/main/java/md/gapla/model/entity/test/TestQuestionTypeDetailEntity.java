package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "testquestiontypedetail", schema = "public")
@Data
public class TestQuestionTypeDetailEntity {
    @Id
    @Column(name = "testquestiontypedetailid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testQuestionTypeDetailId;

    @Column(name = "testquestiontypelanguageid")
    private Long testQuestionTypeLanguageId;

    @Column(name = "value")
    private String value;
}
