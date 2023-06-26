package md.gapla.repository.lesson;

import md.gapla.model.entity.lessons.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long>, JpaSpecificationExecutor {
    List<LessonEntity> findByCourseCourseId(Long courseId);
}
