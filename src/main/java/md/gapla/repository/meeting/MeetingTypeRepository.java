package md.gapla.repository.meeting;

import md.gapla.model.entity.meeting.MeetingEntity;
import md.gapla.model.entity.meeting.MeetingTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingTypeRepository extends JpaRepository<MeetingTypeEntity, Long>, JpaSpecificationExecutor {
}
