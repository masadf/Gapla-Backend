package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonInfoEntity;
import md.gapla.model.entity.common.CommonInfoTypeEntity;
import md.gapla.model.entity.common.CommonInfoTypeLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonInfoTypeRepository extends JpaRepository<CommonInfoTypeEntity, Long> {

    Optional<CommonInfoTypeEntity> findByCommonInfoType(String commonInfoType);

}
