package md.gapla.model.entity.lessons;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;

@Entity
@Table(name = "lessonmaterialstypelanguage", schema = "public")
@Data
public class LessonMaterialsTypeLanguageEntity {
    @Id
    @Column(name = "lessonmaterialstypelanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonMaterialsTypeLanguageId;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "lessonmaterialstypeid", referencedColumnName = "lessonmaterialstypeid")
    private LessonMaterialsTypeEntity lessonMaterialsType;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;
}
