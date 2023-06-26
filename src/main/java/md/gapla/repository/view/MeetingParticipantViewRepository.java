package md.gapla.repository.view;

import md.gapla.model.entity.view.MeetingParticipantViewEntity;
import md.gapla.model.entity.view.MeetingViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingParticipantViewRepository extends JpaRepository<MeetingParticipantViewEntity, Long>, JpaSpecificationExecutor {


}
