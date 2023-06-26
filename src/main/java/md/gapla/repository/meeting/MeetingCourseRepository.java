package md.gapla.repository.meeting;

import md.gapla.model.entity.meeting.MeetingCourseEntity;
import md.gapla.model.entity.meeting.MeetingTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingCourseRepository extends JpaRepository<MeetingCourseEntity, Long>, JpaSpecificationExecutor {
}
