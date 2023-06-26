package md.gapla.repository.meeting;

import md.gapla.model.entity.meeting.MeetingTypeLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingTypeLanguageRepository extends JpaRepository<MeetingTypeLanguageEntity, Long>, JpaSpecificationExecutor {
}
