package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.enums.CommonInfoTypeEnum;

@Entity
@Table(name = "commoninfotypelanguage", schema = "public")
@Data
public class CommonInfoTypeLanguageEntity {
    @Id
    @Column(name = "commoninfotypelanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonInfoLanguageId;

    @Column(name = "commoninfotypeid")
    private Long commonInfoTypeId;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;
}
