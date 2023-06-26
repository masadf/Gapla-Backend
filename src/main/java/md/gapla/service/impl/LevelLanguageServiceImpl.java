package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDetailDto;
import md.gapla.model.dto.view.LevelLanguageViewDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageDetailEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.view.LevelLanguageViewEntity;
import md.gapla.model.input.LevelLanguageDetailInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.levellanguage.LevelLanguageDetailRepository;
import md.gapla.repository.levellanguage.LevelLanguageRepository;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.specification.filters.LevelLanguageViewSpec;
import md.gapla.repository.view.LevelLanguageViewRepository;
import md.gapla.service.LevelLanguageService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static md.gapla.util.ErrorMessagesUtils.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LevelLanguageServiceImpl implements LevelLanguageService {

    private final AppMapper appMapper;
    private final LevelLanguageDetailRepository levelLanguageDetailRepository;
    private final LanguageRepository languageRepository;
    private final LevelLanguageRepository levelLanguageRepository;
    private final LevelLanguageViewRepository levelLanguageViewRepository;

    @Override
    public Page<LevelLanguageViewDto> getLevelLanguageDetailPage(PageParamDto pageParamDto) {


        Specification<LevelLanguageViewEntity> masterSpec = null;

        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new LevelLanguageViewSpec(filterCriteria));
        }

        Page<LevelLanguageViewEntity> list = levelLanguageViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());

        return list.map(appMapper::map);

    }

    @Override
    public List<LevelLanguageDetailDto> getLevelLanguageDetailList(PageParamDto pageParamDto) {
        return null;
    }

    @Override
    public LevelLanguageDetailDto addLevelLanguageDetail(LevelLanguageDetailInput input) {

        LevelLanguageDetailEntity levelLanguageDetailEntity = levelLanguageDetailRepository
                .findByLevelLanguageLevelLanguageIdAndLanguageLanguageCodeIgnoreCase(input.getLevelLanguageId(), input.getLanguageCode());

        if (levelLanguageDetailEntity == null) {
            levelLanguageDetailEntity = createLevelLanguageDetail(input);
        } else {
            levelLanguageDetailEntity = updateLevelLanguageDetail(input, levelLanguageDetailEntity);
        }
        return appMapper.map(levelLanguageDetailEntity);
    }

    @Override
    public LevelLanguageDetailDto updateLevelLanguageDetail(LevelLanguageDetailInput input) {
        LevelLanguageDetailEntity levelLanguageDetailEntity = levelLanguageDetailRepository
                .findByLevelLanguageLevelLanguageIdAndLanguageLanguageCodeIgnoreCase(input.getLevelLanguageId(), input.getLanguageCode());

        if (levelLanguageDetailEntity == null) {
            levelLanguageDetailEntity = createLevelLanguageDetail(input);
        } else {
            levelLanguageDetailEntity = updateLevelLanguageDetail(input, levelLanguageDetailEntity);
        }
        return appMapper.map(levelLanguageDetailEntity);
    }


    private LevelLanguageDetailEntity createLevelLanguageDetail(LevelLanguageDetailInput input) {
        LevelLanguageDetailEntity levelLanguageDetailEntity = new LevelLanguageDetailEntity();
        LevelLanguageEntity levelLanguage = findLevelLanguageById(input.getLevelLanguageId());
        LanguageEntity language = findLanguageByCode(input.getLanguageCode());
        levelLanguageDetailEntity.setLanguage(language);
        levelLanguageDetailEntity.setLevelLanguage(levelLanguage);
        levelLanguageDetailEntity.setLabelValue(input.getLabelValue());
        levelLanguageDetailEntity.setContentValue(input.getContentValue());

        levelLanguageDetailEntity = levelLanguageDetailRepository.save(levelLanguageDetailEntity);
        return levelLanguageDetailEntity;
    }

    public LevelLanguageDetailEntity updateLevelLanguageDetail(LevelLanguageDetailInput input, LevelLanguageDetailEntity levelLanguageDetailEntity) {
        LevelLanguageEntity levelLanguage = findLevelLanguageById(input.getLevelLanguageId());
        LanguageEntity language = findLanguageByCode(input.getLanguageCode());
        levelLanguageDetailEntity.setLanguage(language);
        levelLanguageDetailEntity.setLevelLanguage(levelLanguage);
        levelLanguageDetailEntity.setLabelValue(input.getLabelValue());
        levelLanguageDetailEntity.setContentValue(input.getContentValue());

        levelLanguageDetailEntity = levelLanguageDetailRepository.save(levelLanguageDetailEntity);
        return levelLanguageDetailEntity;
    }

    private LevelLanguageEntity findLevelLanguageById(Long id) {
        return levelLanguageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
    }

    private LanguageEntity findLanguageByCode(String languageCode) {
        return languageRepository.findByLanguageCodeIgnoreCase(languageCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, languageCode)));

    }
}
