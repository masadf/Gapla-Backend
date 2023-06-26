package md.gapla.repository.course;

import md.gapla.model.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>, JpaSpecificationExecutor {

    CourseEntity findByCourseNameIgnoreCase(String courseName);
    Optional<CourseEntity> findByCourseIdAndLanguageLanguageCode(Long courseId, String languageCode);
}
