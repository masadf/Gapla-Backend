package md.gapla.repository.forum;

import md.gapla.model.entity.forum.ForumTypeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForumTypeDetailRepository extends JpaRepository<ForumTypeDetailEntity, Long>, JpaSpecificationExecutor<ForumTypeDetailEntity> {

    List<ForumTypeDetailEntity> findByForumTypeId(Long forumTypeId);

    Optional<ForumTypeDetailEntity> findByForumTypeIdAndValueIgnoreCaseAndLanguageLanguageCodeIgnoreCase(Long forumTypeId, String value, String languageCode);
    Optional<ForumTypeDetailEntity> findByForumTypeIdAndLanguageLanguageCodeIgnoreCase(Long forumTypeId,  String languageCode);
}
