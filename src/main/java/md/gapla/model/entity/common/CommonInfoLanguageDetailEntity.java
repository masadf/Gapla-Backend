package md.gapla.model.entity.common;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "commoninfolanguagedetail", schema = "public")
@Data
public class CommonInfoLanguageDetailEntity {
    @Id
    @Column(name = "commoninfolanguagedetailid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commonInfoLanguageDetailId;

    @Column(name = "value")
    private String value;


    @Column(name = "commoninfolanguageid")
    private Long commonInfoLanguageId;
}
