package md.gapla.repository.levellanguage;

import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelLanguageRepository extends JpaRepository<LevelLanguageEntity, Long> {
}
