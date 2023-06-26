package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonInfoLanguageEntity;
import md.gapla.model.enums.CommonInfoTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonInfoLanguageRepository extends JpaRepository<CommonInfoLanguageEntity, Long>, JpaSpecificationExecutor<CommonInfoLanguageEntity> {
    List<CommonInfoLanguageEntity> findByCommonInfoCommonInfoType(CommonInfoTypeEnum commonInfoType);
}
