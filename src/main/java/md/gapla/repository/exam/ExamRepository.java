package md.gapla.repository.exam;

import md.gapla.model.entity.courseexam.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    List<ExamEntity> findByCoursesCourseId(Long courseId);
    
    ExamEntity findByExamName(String examName);
}
