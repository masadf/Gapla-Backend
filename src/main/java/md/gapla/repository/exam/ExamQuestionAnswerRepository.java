package md.gapla.repository.exam;

import md.gapla.model.entity.courseexam.ExamQuestionAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuestionAnswerRepository extends JpaRepository<ExamQuestionAnswerEntity, Long> {
}
