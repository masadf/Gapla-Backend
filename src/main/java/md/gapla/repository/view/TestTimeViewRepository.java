package md.gapla.repository.view;

import md.gapla.model.entity.view.CommonInfoTypeViewEntity;
import md.gapla.model.entity.view.TimeTypeViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestTimeViewRepository extends JpaRepository<TimeTypeViewEntity, String>, JpaSpecificationExecutor {


}
