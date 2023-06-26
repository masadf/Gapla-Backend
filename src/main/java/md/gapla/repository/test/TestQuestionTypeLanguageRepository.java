package md.gapla.repository.test;

import md.gapla.model.entity.test.TestQuestionTypeLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionTypeLanguageRepository extends JpaRepository<TestQuestionTypeLanguageEntity, Long>, JpaSpecificationExecutor {
    TestQuestionTypeLanguageEntity  findByTestQuestionTypeTestQuestionTypeIdAndLanguageLanguageCode(Long testQuestionTypeId,String languageCode);
}
