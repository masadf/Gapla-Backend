package md.gapla.repository;

import md.gapla.model.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity,Long> {
    Optional<LanguageEntity> findByLanguageCode(String languageCode);
    Optional<LanguageEntity> findByLanguageCodeIgnoreCase(String languageCode);
}
