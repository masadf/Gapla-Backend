package md.gapla.model.dto.view;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingParticipantViewDto {
    private Long meetingParticipantId;

    private Long accountId;

    private String fio;

    private Long meetingsId;

    private LocalDateTime meetingDate;

    private Long meetingTypeId;

    private String type;

    private String value;

    private String languageCode;

}
