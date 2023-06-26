package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.CommonInfoTypeEnum;

@Entity
@Table(name = "commoninfo", schema = "public")
@Data
public class CommonInfoEntity {
    @Id
    @Column(name = "commoninfoid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonInfoId;

    @Column(name = "commoninfocode")
    private String commonInfoCode;
    @ManyToOne
    @JoinColumn(name = "commoninfotypeid", referencedColumnName = "commoninfotypeid")
    private CommonInfoTypeEntity commonInfoType;
}
