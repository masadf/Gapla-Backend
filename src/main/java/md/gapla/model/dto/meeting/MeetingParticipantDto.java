package md.gapla.model.dto.meeting;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.entity.account.AccountEntity;

@Data
public class MeetingParticipantDto {
    private Long meetingParticipantId;
    private AccountDto account;
    private AccountDto createdAccount;
}
