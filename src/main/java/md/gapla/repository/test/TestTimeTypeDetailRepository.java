package md.gapla.repository.test;

import md.gapla.model.entity.test.TestTimeTypeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestTimeTypeDetailRepository extends JpaRepository<TestTimeTypeDetailEntity, Long> {

    List<TestTimeTypeDetailEntity> findByTestTimeTypeLanguageId(Long testTimeTypeLanguageId);
}
