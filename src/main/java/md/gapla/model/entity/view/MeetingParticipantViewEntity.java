package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "meetingparticipantview", schema = "public")
@Data
public class MeetingParticipantViewEntity {
    @Id
    @Column(name = "meetingparticipantid")
    private Long meetingParticipantId;

    @Column(name = "accountid")
    private Long accountId;

    @Column(name = "fio")
    private String fio;

    @Column(name = "meetingsid")
    private Long meetingsId;

    @Column(name = "meetingdate")
    private LocalDateTime meetingDate;

    @Column(name = "meetingtypeid")
    private Long meetingTypeId;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "languagecode")
    private String languageCode;

}
