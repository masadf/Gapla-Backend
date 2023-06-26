package md.gapla.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "languages", schema = "public")
@Data
public class LanguageEntity {
    @Id
    @Column(name = "languageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageId;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "languageimage")
    private String languageImage;
    @Column(name = "languagename")
    private String languageName;
}
