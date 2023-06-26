package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "testtimetypedetail", schema = "public")
@Data
public class TestTimeTypeDetailEntity {
    @Id
    @Column(name = "testtimetypedetailid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testTimeTypeDetailId;

    @Column(name = "value")
    private String value;

    @Column(name = "testtimetypelanguageid")
    private Long testTimeTypeLanguageId;
}
