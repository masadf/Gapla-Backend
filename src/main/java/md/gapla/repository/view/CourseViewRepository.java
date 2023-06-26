package md.gapla.repository.view;

import md.gapla.model.entity.view.CourseViewEntity;
import md.gapla.model.entity.view.LevelLanguageViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseViewRepository extends JpaRepository<CourseViewEntity, Long>, JpaSpecificationExecutor {
}
