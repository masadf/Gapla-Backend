package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumQuestionsCommentsImageEntity;
import md.gapla.model.entity.forum.ForumQuestionsImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumQuestionsCommentsImageRepository extends JpaRepository<ForumQuestionsCommentsImageEntity, Long>, JpaSpecificationExecutor<ForumQuestionsImageEntity> {

    List<ForumQuestionsCommentsImageEntity> findByForumQuestionsCommentId(Long forumQuestionCommentId);
}
