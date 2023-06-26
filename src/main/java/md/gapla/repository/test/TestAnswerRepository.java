package md.gapla.repository.test;

import md.gapla.model.entity.test.TestAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAnswerRepository extends JpaRepository<TestAnswerEntity, Long> {
}
