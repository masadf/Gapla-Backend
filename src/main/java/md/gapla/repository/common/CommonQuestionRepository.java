package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonQuestionRepository extends JpaRepository<CommonQuestionEntity, Long> {
}
