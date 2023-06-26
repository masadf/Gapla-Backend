package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForumTypeRepository extends JpaRepository<ForumTypeEntity, Long> {

    Optional<ForumTypeEntity> findByForumTypeNameIgnoreCase(String forumTypeName);
}
