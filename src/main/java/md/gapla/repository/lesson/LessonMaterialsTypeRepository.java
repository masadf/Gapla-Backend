package md.gapla.repository.lesson;

import md.gapla.model.entity.lessons.LessonMaterialsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonMaterialsTypeRepository extends JpaRepository<LessonMaterialsTypeEntity, Long> , JpaSpecificationExecutor {
}
