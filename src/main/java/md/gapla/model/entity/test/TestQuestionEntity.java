package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testquestion", schema = "public")
@Data
public class TestQuestionEntity {
    @Id
    @Column(name = "testquestionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testQuestionId;

    @ManyToOne
    @JoinColumn(name = "testquestiontypelanguageid", referencedColumnName = "testquestiontypelanguageid")
    TestQuestionTypeLanguageEntity testQuestionTypeLanguage;

    @Column(name = "value")
    private String value;

    @Column(name = "questiontype")
    private String questionType;

    @Column(name = "audiovalue")
    private String audioValue;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @OneToMany(mappedBy = "testQuestionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TestAnswerEntity> variants=new ArrayList<>();
}
