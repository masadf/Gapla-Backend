package md.gapla.repository.view;

import md.gapla.model.entity.view.ForumQuestionCommentsViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumQuestionCommentsViewRepository extends JpaRepository<ForumQuestionCommentsViewEntity, String>, JpaSpecificationExecutor {

}
