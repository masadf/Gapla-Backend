package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.dto.test.TestQuestionTypeDetailDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testquestiontype", schema = "public")
@Data
public class TestQuestionTypeEntity {
    @Id
    @Column(name = "testquestiontypeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testQuestionTypeId;

    @Column(name = "value")
    private String value;

}
