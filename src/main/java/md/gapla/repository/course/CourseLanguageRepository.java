package md.gapla.repository.course;

import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.course.CourseLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseLanguageRepository extends JpaRepository<CourseLanguageEntity, Long>, JpaSpecificationExecutor {

    List<CourseLanguageEntity> findByLanguageLanguageCodeAndCourseIdIn(String languageCode, List<Long> courses);
}
