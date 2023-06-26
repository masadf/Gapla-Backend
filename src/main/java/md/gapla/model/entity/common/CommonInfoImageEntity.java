package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "commoninfoimage", schema = "public")
@Data
public class CommonInfoImageEntity {
    @Id
    @Column(name = "commoninfoimageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commoninfoimageid;

    @Column(name = "commoninfoid")
    private Long commonInfoId;


    @Column(name = "value")
    private Long value;
}
