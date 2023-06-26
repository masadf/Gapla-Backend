package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "testtimetype", schema = "public")
@Data
public class TestTimeTypeEntity {
    @Id
    @Column(name = "testtimetypeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testTimeTypeId;

    @Column(name = "testtime")
    private Long testTime;
}
