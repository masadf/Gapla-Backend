package md.gapla.repository.meeting;

import md.gapla.model.entity.meeting.MeetingParticipantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipantEntity, Long>, JpaSpecificationExecutor {
    MeetingParticipantEntity findByMeetingsIdAndAccountAccountId(Long meetingId, Long AccountId);

    Page<MeetingParticipantEntity> findByMeetingsId(Long meetingsId, Pageable pageRequest);
}
