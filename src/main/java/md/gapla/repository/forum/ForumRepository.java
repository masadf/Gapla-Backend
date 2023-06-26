package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForumRepository extends JpaRepository<ForumEntity, Long>, JpaSpecificationExecutor {
    Optional<ForumEntity> findByForumNameIgnoreCase(String forumName);
    Optional<ForumEntity> findByForumNameIgnoreCaseAndForumTypeDetailForumTypeDetailId(String forumName,Long forumTypeDetailId );

    List<ForumEntity> findByForumTypeDetailForumTypeId(Long forumTypeId);
}
