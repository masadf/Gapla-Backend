package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.meeting.MeetingDto;
import md.gapla.model.dto.view.AccountViewDto;
import md.gapla.model.dto.view.MeetingParticipantViewDto;
import md.gapla.model.dto.view.MeetingTypeViewDto;
import md.gapla.model.dto.view.MeetingViewDto;
import md.gapla.model.input.meeting.MeetingInput;
import md.gapla.model.input.meeting.MeetingParticipantInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MeetingService {
    List<MeetingViewDto> getMeetingByAccount(PageParamDto pageParamDto);

    List<MeetingParticipantViewDto> getMeetingMyMeeting(String token, PageParamDto pageParamDto);

    List<MeetingTypeViewDto> getMeetingType(PageParamDto pageParamDto);

    MeetingDto addMeeting(MeetingInput input);

    void linkCurrentAccountToMeeting(String token, MeetingParticipantInput input);
    void linkAccountToMeeting(String token, MeetingParticipantInput input);
    void unlinkAccountToMeeting(String token, MeetingParticipantInput input);

    MeetingViewDto createMeeting(String token, Object input);
    void updateMeeting(String token, Object input);
    Object getMeeting(Long meetingId);

    MeetingDto deleteParticipant(Long meetingParticipantId);

    MeetingDto updateMeeting(MeetingInput input);

    Page<AccountDto> getParticipantById(Long meetingId,PageParamDto pageParamDto);

    void deleteMeetingById(Long meetingId);
}
