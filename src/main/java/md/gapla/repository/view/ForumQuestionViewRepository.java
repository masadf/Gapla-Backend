package md.gapla.repository.view;

import md.gapla.model.entity.view.ForumQuestionViewEntity;
import md.gapla.model.entity.view.ForumsViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumQuestionViewRepository extends JpaRepository<ForumQuestionViewEntity, Long>, JpaSpecificationExecutor {

}
