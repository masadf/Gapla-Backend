package md.gapla.repository.common;

import md.gapla.model.entity.common.CommonInfoImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonInfoImageRepository extends JpaRepository<CommonInfoImageEntity, Long> {
}
