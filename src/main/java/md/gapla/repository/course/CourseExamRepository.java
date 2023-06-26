package md.gapla.repository.course;

import md.gapla.model.entity.courseexam.CourseExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseExamRepository extends JpaRepository<CourseExamEntity, Long> {

    CourseExamEntity findByExamIdAndCourseId(Long examId, Long courseId);
}
