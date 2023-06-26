package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.ids.TestQuestionTypeTestTimeTypeIds;

import java.io.Serializable;

@Entity
@Table(name = "testquestiontypetesttimetype", schema = "public")
@Data
@IdClass(TestQuestionTypeTestTimeTypeIds.class)
public class TestQuestionTypeTestTimeTypeEntity implements Serializable {
    @Id
    @Column(name = "testtimetypeid")
    private Long testTimeTypeId;

    @Id
    @Column(name = "testquestiontypeid")
    private Long testQuestionTypeId;

}
