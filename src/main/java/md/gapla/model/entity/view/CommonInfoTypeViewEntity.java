package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "commoninfotypeview", schema = "public")
@Data
public class CommonInfoTypeViewEntity {
    @Id
    @Column(name = "commoninfotypecode")
    private String commonInfoTypeCode;

    @Column(name = "value")
    private String value;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "languagename")
    private String languageMame;

    @Column(name = "languageimage")
    private String languageImage;
}
