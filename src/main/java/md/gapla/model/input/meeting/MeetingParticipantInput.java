package md.gapla.model.input.meeting;

import lombok.Data;

import java.util.Set;

@Data
public class MeetingParticipantInput {

    private Long meetingsId;
    private Long accountId;
    private Set<Long> accountIdList;
}
