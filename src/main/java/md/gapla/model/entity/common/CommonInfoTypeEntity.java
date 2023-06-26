package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.CommonInfoTypeEnum;

@Entity
@Table(name = "commoninfotype", schema = "public")
@Data
public class CommonInfoTypeEntity {
    @Id
    @Column(name = "commoninfotypeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonInfoId;

    @Column(name = "commoninfotypecode")
    private String commonInfoType;
}
