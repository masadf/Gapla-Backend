package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "commoninfoview", schema = "public")
@Data
public class CommonInfoViewEntity {
    @Id
    @Column(name = "commoninfocode")
    private String commonInfoCode;


    @Column(name = "commoninfotypecode")
    private String commonInfoTypeCode;

    @Column(name = "labelvalue")
    private String labelValue;

    @Column(name = "contentvalue")
    private String contentValue;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "languageimage")
    private String languageImage;

    @Column(name = "languagename")
    private String languageName;
}
