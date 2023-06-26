package md.gapla.repository.exam;

import md.gapla.model.entity.courseexam.ExamTextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTextRepository extends JpaRepository<ExamTextEntity, Long> {
}
