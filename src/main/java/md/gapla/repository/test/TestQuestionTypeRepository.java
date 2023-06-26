package md.gapla.repository.test;

import md.gapla.model.entity.test.TestQuestionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionTypeRepository extends JpaRepository<TestQuestionTypeEntity, Long> {
}
