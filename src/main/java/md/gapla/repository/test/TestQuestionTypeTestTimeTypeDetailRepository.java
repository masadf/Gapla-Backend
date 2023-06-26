package md.gapla.repository.test;

import md.gapla.model.entity.test.TestQuestionTypeTestTimeTypeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionTypeTestTimeTypeDetailRepository extends JpaRepository<TestQuestionTypeTestTimeTypeDetailEntity, Long> {

    List<TestQuestionTypeTestTimeTypeDetailEntity> findByTestTimeTypeIdAndTestQuestionTypeIdAndLanguageLanguageCode(Long testTimeTypeId, Long testQuestionTypeId,String languageCode);
}
