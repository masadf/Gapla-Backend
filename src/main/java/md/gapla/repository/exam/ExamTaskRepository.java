package md.gapla.repository.exam;

import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTaskRepository   extends JpaRepository<ExamTaskEntity,Long> {
}
