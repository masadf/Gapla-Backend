package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "levellanguageview", schema = "public")
@Data
public class LevelLanguageViewEntity {
    @Id
    @Column(name = "levellanguagedetailid")
    private Long levelLanguageDetailId;


    @Column(name = "labelvalue")
    private String labelValue;

    @Column(name = "contentvalue")
    private String contentValue;

    @Column(name = "levellanguagename")
    private String levelLanguageName;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "languagename")
    private String languageName;

}
