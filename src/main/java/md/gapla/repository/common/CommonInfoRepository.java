package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonInfoRepository extends JpaRepository<CommonInfoEntity, Long> {

    Optional<CommonInfoEntity> findByCommonInfoCode(String commonInfoCode);

    List<CommonInfoEntity> findByCommonInfoTypeCommonInfoType(String commonInfoType);
}
