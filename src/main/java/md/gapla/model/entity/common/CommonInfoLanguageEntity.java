package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.test.TestAnswerEntity;
import md.gapla.model.enums.CommonInfoTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commoninfolanguage", schema = "public")
@Data
public class CommonInfoLanguageEntity {
    @Id
    @Column(name = "commoninfolanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonInfoLanguageId;

    @Column(name = "labelvalue")
    private String labelValue;

    @Column(name = "contentvalue")
    private String contentValue;

    @ManyToOne
    @JoinColumn(name = "commoninfoid", referencedColumnName = "commoninfoid")
    private CommonInfoEntity commonInfo;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;


    @OneToMany(mappedBy = "commonInfoLanguageId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommonInfoLanguageDetailEntity> details =new ArrayList<>();
}
