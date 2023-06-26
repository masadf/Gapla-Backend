package md.gapla.repository.lesson;

import md.gapla.model.entity.lessons.LessonMaterialsTypeLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonMaterialsTypeLanguageRepository extends JpaRepository<LessonMaterialsTypeLanguageEntity, Long>, JpaSpecificationExecutor {
    LessonMaterialsTypeLanguageEntity findByLessonMaterialsTypeLessonMaterialsTypeIdAndLanguageLanguageCode(Long lessonMaterialsTypeId, String languageCode);
}
