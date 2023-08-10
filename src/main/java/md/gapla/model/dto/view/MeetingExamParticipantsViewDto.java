package md.gapla.model.dto.view;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MeetingExamParticipantsViewDto {
	private Long meetingsId;
	private Long meetingTypeId;
	private LocalDateTime meetingDate;
	private ObjectStatusEnum status;
	private Long createdAccount;
	private List<Long> participants = new ArrayList<>();
}
