package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumQuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumQuestionsRepository extends JpaRepository<ForumQuestionsEntity, Long>, JpaSpecificationExecutor<ForumQuestionsEntity> {
}
