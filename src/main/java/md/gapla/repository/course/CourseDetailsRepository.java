package md.gapla.repository.course;

import md.gapla.model.entity.course.CourseDetailsEntity;
import md.gapla.model.entity.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetailsEntity, Long>, JpaSpecificationExecutor {

    List<CourseDetailsEntity> findByCourseId(Long courseId);
}
