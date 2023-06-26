package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.repository.test.TestTimeTypeDetailRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testtimetypelanguage", schema = "public")
@Data
public class TestTimeTypeLanguageEntity {
    @Id
    @Column(name = "testtimetypelanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testTimeTypeLanguageId;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "testtimetypeid", referencedColumnName = "testtimetypeid")
    private TestTimeTypeEntity testTimeType;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

    @OneToMany(mappedBy = "testTimeTypeLanguageId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TestTimeTypeDetailEntity> details=new ArrayList<>();

}
