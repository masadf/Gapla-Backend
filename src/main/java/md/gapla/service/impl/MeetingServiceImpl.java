package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.meeting.MeetingDto;
import md.gapla.model.dto.meeting.OnlineLessonDto;
import md.gapla.model.dto.view.MeetingExamParticipantsViewDto;
import md.gapla.model.dto.view.MeetingParticipantViewDto;
import md.gapla.model.dto.view.MeetingTypeViewDto;
import md.gapla.model.dto.view.MeetingViewDto;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.meeting.MeetingCourseEntity;
import md.gapla.model.entity.meeting.MeetingEntity;
import md.gapla.model.entity.meeting.MeetingParticipantEntity;
import md.gapla.model.entity.meeting.MeetingTypeEntity;
import md.gapla.model.entity.view.MeetingParticipantViewEntity;
import md.gapla.model.entity.view.MeetingTypeViewEntity;
import md.gapla.model.entity.view.MeetingViewEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.meeting.MeetingInput;
import md.gapla.model.input.meeting.MeetingParticipantInput;
import md.gapla.model.input.meeting.OnlineLessonMeetingInput;
import md.gapla.repository.meeting.MeetingCourseRepository;
import md.gapla.repository.meeting.MeetingParticipantRepository;
import md.gapla.repository.meeting.MeetingRepository;
import md.gapla.repository.meeting.MeetingTypeRepository;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.specification.filters.MeetingParticipantViewSpec;
import md.gapla.repository.specification.filters.MeetingTypeViewSpec;
import md.gapla.repository.specification.filters.MeetingViewSpec;
import md.gapla.repository.view.MeetingParticipantViewRepository;
import md.gapla.repository.view.MeetingTypeViewRepository;
import md.gapla.repository.view.MeetingViewRepository;
import md.gapla.service.AccountService;
import md.gapla.service.CourseService;
import md.gapla.service.MeetingService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final AppMapper appMapper;

    private final AccountService accountService;
    private final MeetingRepository meetingRepository;
    private final MeetingParticipantRepository meetingParticipantRepository;
    private final MeetingTypeRepository meetingTypeRepository;

    private final MeetingTypeViewRepository meetingTypeViewRepository;

    private final MeetingParticipantViewRepository meetingParticipantViewRepository;

    private final MeetingViewRepository meetingViewRepository;

    private final MeetingCourseRepository meetingCourseRepository;
    private final CourseService courseService;

    @Override
    public List<MeetingViewDto> getMeetingByAccount(PageParamDto pageParamDto) {

        Specification<MeetingViewEntity> masterSpec = null;

        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new MeetingViewSpec(filterCriteria));
        }
        List<MeetingViewEntity> list = meetingViewRepository.findAll(masterSpec);

        return list.stream().map(appMapper::map).toList();
    }
    
    public List<MeetingExamParticipantsViewDto> getMeetingExamParticipantsByDate(LocalDateTime meetingDateTime){
        List<MeetingEntity> meetings = meetingRepository.findAllByMeetingDate(meetingDateTime);
        return meetings.stream().map(appMapper::mapToView).toList();
    }

    @Override
    public List<MeetingParticipantViewDto> getMeetingMyMeeting(String token, PageParamDto pageParamDto) {

        FilterCriteria criteria = new FilterCriteria();
        criteria.setKey("accountId");
        criteria.setValue(accountService.getEntityByToken(token).getAccountId());

        Specification<MeetingParticipantViewEntity> masterSpec = Specification.where(new MeetingParticipantViewSpec(criteria));

        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new MeetingParticipantViewSpec(filterCriteria));
        }
        List<MeetingParticipantViewEntity> list = meetingParticipantViewRepository.findAll(masterSpec);

        return list.stream().map(appMapper::map).toList();
    }

    @Override
    public List<MeetingTypeViewDto> getMeetingType(PageParamDto pageParamDto) {

        Specification<MeetingTypeViewEntity> masterSpec = null;

        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new MeetingTypeViewSpec(filterCriteria));
        }

        List<MeetingTypeViewEntity> list = meetingTypeViewRepository.findAll(masterSpec);


        return list.stream().map(appMapper::map).toList();
    }

    @Override
    public MeetingDto addMeeting(MeetingInput input) {
        MeetingEntity meetingEntity = fillMeeting(new MeetingEntity(), input);
        meetingEntity = meetingRepository.save(meetingEntity);
        return appMapper.map(meetingEntity);
    }

    @Override
    @Transactional
    public void linkCurrentAccountToMeeting(String token, MeetingParticipantInput input) {
        MeetingViewEntity ll = meetingViewRepository.findByMeetingsIdAndLanguageCode(input.getMeetingsId(), "RU");

        if (Objects.equals(ll.getActiveCount(), ll.getMaxCount())) {
            throw new InvalidRequestException("Max active user");
        }
        AccountEntity accountEntity = accountService.getEntityByToken(token);

        verifyAccountInSelectedMeeting(input.getMeetingsId(), accountEntity.getAccountId());

        MeetingParticipantEntity meetingEntity = new MeetingParticipantEntity();
        meetingEntity.setMeetingsId(input.getMeetingsId());
        meetingEntity.setAccount(accountEntity);
        meetingParticipantRepository.save(meetingEntity);
    }

    @Override
    @Transactional
    public void unlinkAccountToMeeting(String token, MeetingParticipantInput input) {
        for (Long accountId : input.getAccountIdList()) {
            MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository.findByMeetingsIdAndAccountAccountId(input.getMeetingsId(), accountId);

            if (meetingParticipantEntity == null) {
                throw new EntityNotFoundException("Not found the participant");
            }
            meetingParticipantRepository.delete(meetingParticipantEntity);
        }
    }

    @Override
    @Transactional
    public void linkAccountToMeeting(String token, MeetingParticipantInput input) {
        MeetingViewEntity ll = meetingViewRepository.findByMeetingsIdAndLanguageCode(input.getMeetingsId(), "RU");

        if (Objects.equals(ll.getActiveCount(), ll.getMaxCount())) {
            throw new InvalidRequestException("Max active user");
        }

        for (Long accountId : input.getAccountIdList()) {
            AccountEntity accountEntity = accountService.get(accountId);

            verifyAccountInSelectedMeeting(input.getMeetingsId(), accountEntity.getAccountId());
            MeetingParticipantEntity meetingEntity = new MeetingParticipantEntity();
            meetingEntity.setMeetingsId(input.getMeetingsId());
            meetingEntity.setAccount(accountEntity);
            meetingParticipantRepository.save(meetingEntity);
        }

    }

    @Override
    @Transactional
    public MeetingViewDto createMeeting(String token, Object input) {
        MeetingViewEntity meetingEntity = verifyType(token, input);

        return appMapper.map(meetingEntity);
    }

    @Override
    public void updateMeeting(String token, Object input) {
        verifyForUpdateType(token, input);
    }

    @Override
    public Object getMeeting(Long meetingId) {

        return verifyForGetType(meetingRepository.findByMeetingsId(meetingId));
    }


    @Override
    public MeetingDto deleteParticipant(Long meetingParticipantId) {
        return null;
    }

    @Override
    public MeetingDto updateMeeting(MeetingInput input) {
        MeetingEntity meetingEntity = meetingRepository.findById(input.getMeetingsId())
                .orElseThrow(() -> new EntityNotFoundException(""));
        meetingEntity = fillMeeting(meetingEntity, input);
        return appMapper.map(meetingRepository.save(meetingEntity));
    }

    @Override
    public Page<AccountDto> getParticipantById(Long meetingId, PageParamDto pageParamDto) {
        Page<AccountEntity> accountEntities = meetingParticipantRepository.findByMeetingsId(meetingId, pageParamDto.getPageRequest()).map(MeetingParticipantEntity::getAccount);
        return accountEntities.map(appMapper::map);
    }

    @Override
    public void deleteMeetingById(Long meetingId) {
        MeetingEntity meetingEntity = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new EntityNotFoundException(""));
        meetingEntity.setStatus(ObjectStatusEnum.DISABLE);
        meetingRepository.save(meetingEntity);
    }

    MeetingEntity fillMeeting(MeetingEntity meetingEntity, MeetingInput input) {
        meetingEntity.setDescription(input.getDesctiption());
        meetingEntity.setTitle(input.getTitle());
        meetingEntity.setCreatedAccount(new AccountEntity(8L));
        meetingEntity.setLinkUrl(input.getLinkUrl());
        List<AccountEntity> list = new ArrayList<>();
        input.getParticipants().forEach(r -> {
            AccountEntity accountEntity = accountService.get(r);
            list.add(accountEntity);
        });
        meetingEntity.setParticipants(list);
        return meetingEntity;
    }

    private void verifyAccountInSelectedMeeting(Long meetingId, Long accountId) {
        MeetingParticipantEntity meetingParticipantEntity = meetingParticipantRepository.findByMeetingsIdAndAccountAccountId(meetingId, accountId);
        if (meetingParticipantEntity != null) {
            throw new InvalidRequestException("Current account is linked to the meeting");
        }
    }

    private MeetingViewEntity verifyType(String token, Object input) {
        if (input instanceof OnlineLessonMeetingInput) {
            MeetingEntity meetingEntity = createOnlineMeeting(token, (OnlineLessonMeetingInput) input);
            MeetingViewEntity meetingViewEntity = meetingViewRepository.findByMeetingsIdAndLanguageCode(meetingEntity.getMeetingsId(), ((OnlineLessonMeetingInput) input).getLanguageCode());
            return meetingViewEntity;
        }
        return null;
    }

    private void verifyForUpdateType(String token, Object input) {
        if (input instanceof OnlineLessonMeetingInput) {
            createOnlineMeeting(token, (OnlineLessonMeetingInput) input);
        }
    }

    private Object verifyForGetType(MeetingEntity meetingEntity) {
        if (meetingEntity.getMeetingTypeId() == 6) {
            return getOnlineMeeting(meetingEntity);
        }
        return null;
    }

    private OnlineLessonDto getOnlineMeeting(MeetingEntity meetingEntity) {
    
        return appMapper.mapToOnlineLessonDto(meetingEntity);
    }

    private MeetingEntity createOnlineMeeting(String token, OnlineLessonMeetingInput input) {
        AccountEntity currentAccount = accountService.getEntityByToken(token);
        MeetingEntity meetingEntity;
        if (input.getMeetingsId() == null) {
            meetingEntity = new MeetingEntity();
        } else {
            meetingEntity = meetingRepository.findByMeetingsId(input.getMeetingsId());
        }
        meetingEntity.setMeetingDate(LocalDateTime.parse(input.getMeetingDate()));
        meetingEntity.setMaxCount(input.getMaxCount());
        meetingEntity.setTitle(input.getTitle());
        meetingEntity.setDescription(input.getDesctiption());
        meetingEntity.setLinkUrl(input.getLinkUrl());
        meetingEntity.setCreatedAccount(currentAccount);
        MeetingTypeEntity meetingTypeEntity = getMeetingTypeById(input.getMeetingTypeId());
        meetingEntity.setMeetingTypeId(meetingTypeEntity.getMeetingTypeId());
        meetingEntity.setRecordedVideo(input.getRecordedVideo());

        meetingEntity = meetingRepository.save(meetingEntity);

        CourseEntity course = courseService.getCourseEntity(input.getCourseId());
        if (input.getMeetingsId() == null) {
            MeetingCourseEntity meetingCourseEntity = new MeetingCourseEntity();
            meetingCourseEntity.setMeetingId(meetingEntity.getMeetingsId());
            meetingCourseEntity.setCourseId(course.getCourseId());
            meetingCourseRepository.save(meetingCourseEntity);
        }
        return meetingEntity;
    }


    private MeetingTypeEntity getMeetingTypeById(Long id) {
        return meetingTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
    }
}
