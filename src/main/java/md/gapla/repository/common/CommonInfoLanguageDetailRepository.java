package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonInfoLanguageDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonInfoLanguageDetailRepository extends JpaRepository<CommonInfoLanguageDetailEntity, Long> {
}
