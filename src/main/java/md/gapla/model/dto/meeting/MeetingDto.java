package md.gapla.model.dto.meeting;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.meeting.MeetingParticipantEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MeetingDto {
    private Long meetingsId;
    private String title;
    private String description;
    private String linkUrl;
    private LocalDate createdDate;
    private LocalDate meetingDate;
    private ParticipantDto createdAccount;
    private List<ParticipantDto> participants =new ArrayList<>();
}
