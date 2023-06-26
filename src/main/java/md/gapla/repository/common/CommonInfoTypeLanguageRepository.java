package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonInfoTypeLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonInfoTypeLanguageRepository extends JpaRepository<CommonInfoTypeLanguageEntity, Long> {

    CommonInfoTypeLanguageEntity findByLanguageLanguageCodeAndCommonInfoTypeId(String languageCode,Long commonInfoTypeId);
    List<CommonInfoTypeLanguageEntity> findByLanguageLanguageCode(String languageCode);

}
