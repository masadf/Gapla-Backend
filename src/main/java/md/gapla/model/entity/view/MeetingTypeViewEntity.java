package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "meetingtypeview", schema = "public")
@Data
public class MeetingTypeViewEntity {
    @Id
    @Column(name = "meetingtypelanguageid")
    private Long meetingTypeLanguageId;

    @Column(name = "meetingtypeid")
    private Long meetingTypeId;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @Column(name = "languagecode")
    private String languageCode;

}
