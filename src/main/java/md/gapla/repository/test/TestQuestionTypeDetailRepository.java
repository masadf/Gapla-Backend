package md.gapla.repository.test;

import md.gapla.model.entity.test.TestQuestionTypeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionTypeDetailRepository extends JpaRepository<TestQuestionTypeDetailEntity, Long> {
}
