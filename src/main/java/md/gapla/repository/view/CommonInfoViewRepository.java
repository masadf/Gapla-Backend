package md.gapla.repository.view;

import md.gapla.model.entity.view.CommonInfoTypeViewEntity;
import md.gapla.model.entity.view.CommonInfoViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonInfoViewRepository extends JpaRepository<CommonInfoViewEntity, String>, JpaSpecificationExecutor {
}
