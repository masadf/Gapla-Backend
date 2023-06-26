package md.gapla.repository.test;

import md.gapla.model.entity.test.TestTextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTextRepository extends JpaRepository<TestTextEntity, Long> {
}
