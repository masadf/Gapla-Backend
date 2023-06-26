package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testquestiontypelanguage", schema = "public")
@Data
public class TestQuestionTypeLanguageEntity {
    @Id
    @Column(name = "testquestiontypelanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testQuestionTypeLanguageId;

    @Column(name = "value")
    private String value;
    @ManyToOne
    @JoinColumn(name = "testquestiontypeid", referencedColumnName = "testquestiontypeid")
    private TestQuestionTypeEntity testQuestionType;
    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

    @OneToMany(mappedBy = "testQuestionTypeLanguageId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TestQuestionTypeDetailEntity> details=new ArrayList<>();
}
