package md.gapla.repository.view;

import md.gapla.model.entity.view.MeetingTypeViewEntity;
import md.gapla.model.entity.view.MeetingViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingViewRepository extends JpaRepository<MeetingViewEntity, Long>, JpaSpecificationExecutor {

    MeetingViewEntity findByMeetingsIdAndLanguageCode(Long id,String languageCode);

}
