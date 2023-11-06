package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.meeting.MeetingDto;
import md.gapla.model.dto.view.MeetingExamParticipantsViewDto;
import md.gapla.model.dto.view.MeetingParticipantViewDto;
import md.gapla.model.dto.view.MeetingTypeViewDto;
import md.gapla.model.dto.view.MeetingViewDto;
import md.gapla.model.input.meeting.MeetingInput;
import md.gapla.model.input.meeting.MeetingParticipantInput;
import md.gapla.model.input.meeting.OnlineLessonMeetingInput;
import md.gapla.service.MeetingService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController(value = "meeting")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/meeting")
@AllArgsConstructor
@CrossOrigin
public class MeetingController {
    
    private final MeetingService meetingService;
    
    @PostMapping(value = "/type/list")
    public ResponseEntity<List<MeetingTypeViewDto>> getMeetingType(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(meetingService.getMeetingType(pageParamDto));
    }
    
    
    @PostMapping(value = "/list")
    public ResponseEntity<List<MeetingViewDto>> getMeeting(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(meetingService.getMeetingByAccount(pageParamDto));
    }
    
    @PostMapping(value = "/list/my")
    public ResponseEntity<List<MeetingParticipantViewDto>> getMyMeeting(@RequestHeader("AuthorizationToken") String token,
                                                                        @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(meetingService.getMeetingMyMeeting(token, pageParamDto));
    }
    
    @PostMapping(value = "/link")
    public ResponseEntity<Void> linkCurrentAccountToMeeting(@RequestHeader("AuthorizationToken") String token,
                                                            @RequestBody MeetingParticipantInput input) {
        meetingService.linkCurrentAccountToMeeting(token, input);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(value = "/teach/link")
    public ResponseEntity<Void> linkAccountToMeeting(@RequestHeader("AuthorizationToken") String token,
                                                     @RequestBody MeetingParticipantInput input) {
        meetingService.linkAccountToMeeting(token, input);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(value = "/teach/unlink")
    public ResponseEntity<Void> unlinkAccountToMeeting(@RequestHeader("AuthorizationToken") String token,
                                                       @RequestBody MeetingParticipantInput input) {
        meetingService.unlinkAccountToMeeting(token, input);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(value = "/lesson-online/add")
    public ResponseEntity<MeetingViewDto> createMeeting(@RequestHeader("AuthorizationToken") String token, @RequestBody OnlineLessonMeetingInput input) {
        MeetingViewDto meetingViewDto = meetingService.createMeeting(token, input);
        return ResponseEntity.ok(meetingViewDto);
    }
    
    @PutMapping(value = "/lesson-online/update")
    public ResponseEntity<MeetingViewDto> updateMeeting(@RequestHeader("AuthorizationToken") String token, @RequestBody OnlineLessonMeetingInput input) {
        return ResponseEntity.ok(meetingService.createMeeting(token, input));
    }
    
    @GetMapping(value = "/lesson-online/{meetingId}")
    public ResponseEntity<Object> getOnlineLesson(@PathVariable("meetingId") Long meetingId) {
        
        
        return ResponseEntity.ok(meetingService.getMeeting(meetingId));
    }
    
    @PostMapping(value = "/participants/{meetingId}")
    Page<AccountDto> getParticipantById(@PathVariable("meetingId") Long meetingId,
                                        @RequestBody PageParamDto pageParamDto) {
        return meetingService.getParticipantById(meetingId, pageParamDto);
    }
    
    @PostMapping
    public ResponseEntity<MeetingDto> addMeetingsByAccount(@RequestBody MeetingInput input) {
        return ResponseEntity.ok(meetingService.addMeeting(input));
    }
    
    @PutMapping()
    public ResponseEntity<MeetingDto> updateMeetingsByAccount(@RequestBody MeetingInput input) {
        return ResponseEntity.ok(meetingService.updateMeeting(input));
    }
    
    @DeleteMapping(value = "/{meetingId}")
    public ResponseEntity<MeetingDto> deleteMeetingById(@PathVariable Long meetingId) {
        meetingService.deleteMeetingById(meetingId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/by-date")
    public ResponseEntity<List<MeetingExamParticipantsViewDto>> getMeetingExamParticipantsByDate(@RequestParam("date") LocalDateTime date) {
        List<MeetingExamParticipantsViewDto> meetings = meetingService.getMeetingExamParticipantsByDate(date);
        return ResponseEntity.ok(meetings);
    }
}
