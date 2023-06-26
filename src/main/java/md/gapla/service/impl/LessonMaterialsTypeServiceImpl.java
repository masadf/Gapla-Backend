package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeLanguageDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeLanguageEntity;
import md.gapla.model.input.lesson.LessonMaterialsTypeLanguageInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.lesson.LessonMaterialsTypeLanguageRepository;
import md.gapla.repository.lesson.LessonMaterialsTypeRepository;
import md.gapla.service.LessonMaterialsTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static md.gapla.repository.specification.LessonMaterialTypeLanguageSpec.languageCodeEqual;
import static md.gapla.repository.specification.LessonMaterialTypeLanguageSpec.lessonMaterialsTypeIdEqual;
import static md.gapla.util.ErrorMessagesUtils.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LessonMaterialsTypeServiceImpl implements LessonMaterialsTypeService {
    private final AppMapper appMapper;

    private final LanguageRepository languageRepository;

    private final LessonMaterialsTypeRepository lessonMaterialsTypeRepository;

    private final LessonMaterialsTypeLanguageRepository lessonMaterialsTypeLanguageRepository;


    @Override
    public Page<LessonMaterialsTypeDto> getLessonMaterialsTypeDtoPage(PageParamDto pageParamDto) {

        Map<String, String> params = pageParamDto.getParams();

        return lessonMaterialsTypeRepository.findAll(pageParamDto.getPageRequest()).map(appMapper::map);
    }

    @Override
    public Page<LessonMaterialsTypeLanguageDto> getLessonMaterialsTypeLanguagePage(Long lessonMaterialsTypeId, PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();
        String languageCode = params.get("languageCode");

        Specification<LessonMaterialsTypeLanguageEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode.toUpperCase()) : null)
                .and(lessonMaterialsTypeId != null ? lessonMaterialsTypeIdEqual(Long.valueOf(lessonMaterialsTypeId)) : null);

        Page<LessonMaterialsTypeLanguageEntity> lessonMaterialsTypeLanguages = lessonMaterialsTypeLanguageRepository
                .findAll(specification, pageParamDto.getPageRequest());

        return lessonMaterialsTypeLanguages.map(appMapper::map);
    }

    @Override
    public List<LessonMaterialsTypeLanguageDto> getLessonMaterialsTypeLanguageList(String languageCode, PageParamDto pageParamDto) {
        return null;
    }

    @Transactional
    @Override
    public LessonMaterialsTypeLanguageDto addLessonMaterialsTypeLanguage(LessonMaterialsTypeLanguageInput input) {
        LessonMaterialsTypeLanguageEntity lessonMaterialsTypeLanguageEntity = lessonMaterialsTypeLanguageRepository
                .findByLessonMaterialsTypeLessonMaterialsTypeIdAndLanguageLanguageCode(input.getLessonMaterialsTypeId(), input.getLanguageCode());
        if (lessonMaterialsTypeLanguageEntity == null) {
            lessonMaterialsTypeLanguageEntity = createLessonMaterialsTypeLanguage(input);
        } else {
            lessonMaterialsTypeLanguageEntity = updateLessonMaterialsTypeLanguage(lessonMaterialsTypeLanguageEntity, input);
        }

        return appMapper.map(lessonMaterialsTypeLanguageEntity);
    }

    @Transactional
    @Override
    public LessonMaterialsTypeLanguageDto updateLessonMaterialsTypeLanguage(LessonMaterialsTypeLanguageInput input) {
        LessonMaterialsTypeLanguageEntity lessonMaterialsTypeLanguageEntity = lessonMaterialsTypeLanguageRepository
                .findByLessonMaterialsTypeLessonMaterialsTypeIdAndLanguageLanguageCode(input.getLessonMaterialsTypeId(), input.getLanguageCode());
        if (lessonMaterialsTypeLanguageEntity == null) {
            lessonMaterialsTypeLanguageEntity = createLessonMaterialsTypeLanguage(input);
        } else {
            lessonMaterialsTypeLanguageEntity = updateLessonMaterialsTypeLanguage(lessonMaterialsTypeLanguageEntity, input);
        }

        return appMapper.map(lessonMaterialsTypeLanguageEntity);
    }


    private LessonMaterialsTypeLanguageEntity createLessonMaterialsTypeLanguage(LessonMaterialsTypeLanguageInput input) {
        LessonMaterialsTypeLanguageEntity lessonMaterialsTypeLanguageEntity = new LessonMaterialsTypeLanguageEntity();

        LanguageEntity language = findLanguageByCode(input.getLanguageCode());

        LessonMaterialsTypeEntity lessonMaterialsType = lessonMaterialsTypeRepository.findById(input.getLessonMaterialsTypeId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        lessonMaterialsTypeLanguageEntity.setLanguage(language);
        lessonMaterialsTypeLanguageEntity.setValue(input.getValue());
        lessonMaterialsTypeLanguageEntity.setLessonMaterialsType(lessonMaterialsType);
        lessonMaterialsTypeLanguageEntity = lessonMaterialsTypeLanguageRepository.save(lessonMaterialsTypeLanguageEntity);

        return lessonMaterialsTypeLanguageEntity;
    }

    private LessonMaterialsTypeLanguageEntity updateLessonMaterialsTypeLanguage(LessonMaterialsTypeLanguageEntity lessonMaterialsTypeLanguageEntity, LessonMaterialsTypeLanguageInput input) {

        LanguageEntity language = findLanguageByCode(input.getLanguageCode());

        LessonMaterialsTypeEntity lessonMaterialsType = lessonMaterialsTypeRepository.findById(input.getLessonMaterialsTypeId())
                .orElseThrow(() -> new EntityNotFoundException(""));

        lessonMaterialsTypeLanguageEntity.setLanguage(language);
        lessonMaterialsTypeLanguageEntity.setLessonMaterialsType(lessonMaterialsType);
        lessonMaterialsTypeLanguageEntity.setValue(input.getValue());

        lessonMaterialsTypeLanguageEntity = lessonMaterialsTypeLanguageRepository.save(lessonMaterialsTypeLanguageEntity);
        return lessonMaterialsTypeLanguageEntity;
    }

    private LanguageEntity findLanguageByCode(String languageCode) {
        return languageRepository.findByLanguageCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, languageCode)));

    }

}
