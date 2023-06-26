package md.gapla.repository.test;

import md.gapla.model.entity.ids.TestQuestionTypeTestTimeTypeIds;
import md.gapla.model.entity.test.TestQuestionTypeTestTimeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionTypeTestTimeTypeRepository extends JpaRepository<TestQuestionTypeTestTimeTypeEntity, TestQuestionTypeTestTimeTypeIds> {

    List<TestQuestionTypeTestTimeTypeEntity> findByTestTimeTypeId(Long testTimeTypeId);

    List<TestQuestionTypeTestTimeTypeEntity> findByTestQuestionTypeId(Long testQuestionTypeId);
}
