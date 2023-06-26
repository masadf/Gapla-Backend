package md.gapla.repository.exam;

import md.gapla.model.entity.courseexam.ExamQuestionTaskTextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionTaskTextRepository extends JpaRepository<ExamQuestionTaskTextEntity, Long> {

    List<ExamQuestionTaskTextEntity> findByExamTaskId(Long examTaskId);
}
