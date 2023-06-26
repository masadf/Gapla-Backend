package md.gapla.repository.test;

import md.gapla.model.entity.test.TestTimeTypeLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestTimeTypeLanguageRepository extends JpaRepository<TestTimeTypeLanguageEntity, Long> , JpaSpecificationExecutor {
    Optional<TestTimeTypeLanguageEntity> findByLanguageLanguageCodeAndTestTimeTypeLanguageId(String languageCode,Long testTimeTypeLanguageId);
//    Optional<TestTimeTypeLanguageEntity> findByLanguageLanguageCodeAndTestTimeTypeTestTimeTypeId(String languageCode,Long testTimeTypeLanguageId);
    TestTimeTypeLanguageEntity findByLanguageLanguageCodeAndTestTimeTypeTestTimeTypeId(String languageCode,Long testTimeTypeId);
    TestTimeTypeLanguageEntity findByLanguageLanguageCode(String languageCode);
}
