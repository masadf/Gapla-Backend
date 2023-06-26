package md.gapla.repository.view;

import md.gapla.model.entity.view.CommonInfoViewEntity;
import md.gapla.model.entity.view.LevelLanguageViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelLanguageViewRepository extends JpaRepository<LevelLanguageViewEntity, Long>, JpaSpecificationExecutor {
}
