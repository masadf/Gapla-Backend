package md.gapla.repository.exam;

import md.gapla.model.entity.courseexam.ExamQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuestionRepository  extends JpaRepository<ExamQuestionEntity,Long> {
}
