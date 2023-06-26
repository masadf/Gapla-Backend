package md.gapla.repository.test;

import md.gapla.model.entity.test.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long>, JpaSpecificationExecutor {

    List<TestEntity> findByTestTimeTypeTestTimeTypeIdAndLanguageLanguageCode(Long testTimeTypeId, String languageCode);
}
