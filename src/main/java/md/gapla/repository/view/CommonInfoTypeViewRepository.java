package md.gapla.repository.view;

import md.gapla.model.entity.view.CommonInfoTypeViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonInfoTypeViewRepository extends JpaRepository<CommonInfoTypeViewEntity, String>, JpaSpecificationExecutor {
List<CommonInfoTypeViewEntity> findByLanguageCodeIgnoreCase(String languageCode);

}
