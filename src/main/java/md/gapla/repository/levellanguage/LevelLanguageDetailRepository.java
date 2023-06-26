package md.gapla.repository.levellanguage;

import md.gapla.model.entity.levellanguage.LevelLanguageDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelLanguageDetailRepository extends JpaRepository<LevelLanguageDetailEntity, Long>, JpaSpecificationExecutor {
    LevelLanguageDetailEntity findByLevelLanguageLevelLanguageIdAndLanguageLanguageCodeIgnoreCase(Long levelLanguageId, String languageCode);
}
