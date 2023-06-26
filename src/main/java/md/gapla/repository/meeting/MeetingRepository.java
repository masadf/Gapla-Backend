package md.gapla.repository.meeting;

import md.gapla.model.entity.meeting.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<MeetingEntity, Long>, JpaSpecificationExecutor {
    MeetingEntity findByMeetingsId(Long meetingId);
}
