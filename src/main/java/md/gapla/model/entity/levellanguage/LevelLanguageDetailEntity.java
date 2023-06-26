package md.gapla.model.entity.levellanguage;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;

@Entity
@Table(name = "levellanguagedetail", schema = "public")
@Data
public class LevelLanguageDetailEntity {
    @Id
    @Column(name = "levellanguagedetailid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelLanguageDetailId;

    @Column(name = "labelvalue")
    private String labelValue;

    @Column(name = "contentvalue")
    private String contentValue;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;
    @ManyToOne
    @JoinColumn(name = "levellanguageid", referencedColumnName = "levellanguageid")
    private LevelLanguageEntity levelLanguage;
}
