package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeLanguageDto;
import md.gapla.model.input.lesson.LessonMaterialsTypeLanguageInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LessonMaterialsTypeService {

    Page<LessonMaterialsTypeDto> getLessonMaterialsTypeDtoPage(PageParamDto pageParamDto);

    Page<LessonMaterialsTypeLanguageDto> getLessonMaterialsTypeLanguagePage(Long lessonMaterialsTypeId,PageParamDto pageParamDto);

    List<LessonMaterialsTypeLanguageDto> getLessonMaterialsTypeLanguageList(String languageCode,PageParamDto pageParamDto);

    LessonMaterialsTypeLanguageDto addLessonMaterialsTypeLanguage( LessonMaterialsTypeLanguageInput input);
    LessonMaterialsTypeLanguageDto updateLessonMaterialsTypeLanguage( LessonMaterialsTypeLanguageInput input);
}
