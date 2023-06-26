package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "timetypeview", schema = "public")
@Data
public class TimeTypeViewEntity {
    @Id
    @Column(name = "testtimetypelanguageid")
    private Long testTimeTypeLanguageId;


    @Column(name = "testtimetypeid")
    private Long testTimeTypeId;

    @Column(name = "testtime")
    private String testTime;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "value")
    private String value;

}
