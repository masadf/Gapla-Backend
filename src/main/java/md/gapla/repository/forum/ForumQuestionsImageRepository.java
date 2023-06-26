package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumQuestionsImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumQuestionsImageRepository extends JpaRepository<ForumQuestionsImageEntity, Long>, JpaSpecificationExecutor<ForumQuestionsImageEntity> {

    List<ForumQuestionsImageEntity> findByForumQuestionsId(Long forumQuestionId);
}
