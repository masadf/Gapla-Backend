package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;

@Entity
@Table(name = "commonquestions", schema = "public")
@Data
public class CommonQuestionEntity {
    @Id
    @Column(name = "commonquestionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonQuestionId;

    @Column(name = "questionvalue")
    private String questionValue;

    @Column(name = "answervalue")
    private String answerValue;
    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;
}
