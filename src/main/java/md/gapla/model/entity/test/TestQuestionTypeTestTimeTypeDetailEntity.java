package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;

@Entity
@Table(name = "testquestiontypetesttimetypedetail", schema = "public")
@Data
public class TestQuestionTypeTestTimeTypeDetailEntity {
    @Id
    @Column(name = "testquestiontypetesttimetypedetail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testQuestionTypeTestTimeTypeDetail;

    @Column(name = "testtimetypeid")
    private Long testTimeTypeId;

    @Column(name = "testquestiontypeid")
    private Long testQuestionTypeId;

    @Column(name = "value")
    private String value;


    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

}
