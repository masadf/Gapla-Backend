package md.gapla.model.entity.levellanguage;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;

@Entity
@Table(name = "levellanguage", schema = "public")
@Data
public class LevelLanguageEntity {
    @Id
    @Column(name = "levellanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelLanguageId;

    @Column(name = "rangemin")
    private String rangeMin;

    @Column(name = "rangemax")
    private String rangeMax;
}
