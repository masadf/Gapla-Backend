package md.gapla.repository.test;

import md.gapla.model.entity.test.TestQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestionEntity, Long>, JpaSpecificationExecutor {

    TestQuestionEntity findByTestQuestionTypeLanguageTestQuestionTypeTestQuestionTypeIdAndTestQuestionTypeLanguageLanguageLanguageCode(Long testQuestionTypeId,String languageCode);
}
