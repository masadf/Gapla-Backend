package md.gapla.repository.test;

import md.gapla.model.entity.test.TestTimeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTimeTypeRepository extends JpaRepository<TestTimeTypeEntity, Long>, JpaSpecificationExecutor {
}
