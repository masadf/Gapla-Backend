package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonMaterialsDto;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.model.input.lesson.LessonMaterialsInput;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LessonMaterialsService {
    Page<LessonMaterialsDto> getLessonMaterialsPage(PageParamDto pageParamDto);
    List<LessonMaterialsDto> getLessonMaterialsList(String languageCode,PageParamDto pageParamDto);
    LessonMaterialsDto addLessonMaterials(LessonMaterialsInput input);
    LessonMaterialsDto uploadLessonMaterials(Long documentId, MultipartFile file);
    byte[] downloadLessonMaterials(Long documentId);
    LessonMaterialsDto updateLessonMaterials(LessonMaterialsInput input);
    LessonMaterialsDto getLessonMaterials(Long documentId);
    void deleteLessonMaterials(Long documentId);
    List<LessonMaterialsEntity> getLessonMaterialsByLessonId(Long lessonId);
}
