package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumQuestionsCommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumQuestionsCommentsRepository extends JpaRepository<ForumQuestionsCommentsEntity, Long>, JpaSpecificationExecutor {
}
