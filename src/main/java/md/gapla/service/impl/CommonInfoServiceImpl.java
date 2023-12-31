package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.common.CommonInfoDto;
import md.gapla.model.dto.common.CommonInfoLanguageDto;
import md.gapla.model.dto.common.CommonInfoTypeLanguageDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.common.*;
import md.gapla.model.enums.CommonInfoTypeEnum;
import md.gapla.model.input.CommonInfoLanguageInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.common.CommonInfoLanguageRepository;
import md.gapla.repository.common.CommonInfoRepository;
import md.gapla.repository.common.CommonInfoTypeLanguageRepository;
import md.gapla.repository.common.CommonInfoTypeRepository;
import md.gapla.repository.view.CommonInfoTypeViewRepository;
import md.gapla.service.CommonInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static md.gapla.repository.specification.CommonInfoLanguageSpec.*;
import static md.gapla.util.ErrorMessagesUtils.COMMON_INFO_NOT_FOUND;
import static md.gapla.util.ErrorMessagesUtils.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommonInfoServiceImpl implements CommonInfoService {

    private final AppMapper appMapper;
    private final LanguageRepository languageRepository;
    private final CommonInfoRepository commonInfoRepository;

    private final CommonInfoTypeLanguageRepository commonInfoTypeLanguageRepository;

    private final CommonInfoLanguageRepository commonInfoLanguageRepository;
    private final CommonInfoTypeRepository commonInfoTypeRepository;

    private final CommonInfoTypeViewRepository commonInfoTypeViewRepository;

    @Override
    public List<CommonInfoTypeLanguageDto> findAllCommonInfoTypeByLanguageCode(String languageCode) {

        List ss = commonInfoTypeViewRepository.findByLanguageCodeIgnoreCase(languageCode);
        return commonInfoTypeLanguageRepository.findByLanguageLanguageCode(languageCode).stream().map(appMapper::map).toList();

    }
    
    @Override
    @Transactional
    public Page<CommonInfoDto> findAll(PageParamDto pageParamDto, String languageCode) {


        Page<CommonInfoEntity> commonInfoEntities = commonInfoRepository.findAll(pageParamDto.getPageRequest());
        Page<CommonInfoDto> commonInfoDtos = commonInfoEntities.map(appMapper::map);

        commonInfoDtos.forEach(r -> {
            CommonInfoTypeLanguageEntity commonInfoTypeLanguageEntity = commonInfoTypeLanguageRepository.findByLanguageLanguageCodeAndCommonInfoTypeId(languageCode, findByCode(r.getCommonInfoType().getValue()).getCommonInfoId());
            if (commonInfoTypeLanguageEntity != null)
                r.setValue(commonInfoTypeLanguageEntity.getValue());
        });
        return commonInfoDtos;
    }

    @Override
    public CommonInfoLanguageDto updateCommonInfo(CommonInfoLanguageInput commonInfoLanguageInput) {
        return saveCommonInfo(commonInfoLanguageInput);
    }

    @Override
    @Transactional
    public CommonInfoLanguageDto addCommonInfo(CommonInfoLanguageInput commonInfoLanguageInput) {
        return saveCommonInfo(commonInfoLanguageInput);
    }


    private CommonInfoLanguageEntity updateExistedCommonInfoLanguageEntity(CommonInfoLanguageInput commonInfoLanguageInput, CommonInfoLanguageEntity commonInfoLanguage) {
        commonInfoLanguage.setLabelValue(commonInfoLanguageInput.getLabelValue());
        commonInfoLanguage.setContentValue(commonInfoLanguageInput.getContentValue());
        Long commonInfoLanguageId = commonInfoLanguage.getCommonInfoLanguageId();
        List<CommonInfoLanguageDetailEntity> details = commonInfoLanguageInput.getDetails().stream().map(appMapper::map).toList();
        details.forEach(r -> r.setCommonInfoLanguageId(commonInfoLanguageId));
        commonInfoLanguage.getDetails().clear();
        commonInfoLanguage.getDetails().addAll(details);
        return commonInfoLanguageRepository.save(commonInfoLanguage);
    }

    private CommonInfoLanguageEntity createdNewCommonInfoLanguageEntity(CommonInfoLanguageInput commonInfoLanguageInput) {
        CommonInfoEntity commonInfo = commonInfoRepository.findByCommonInfoCode(commonInfoLanguageInput.getCommonInfoCode())
                .orElseThrow(() -> new EntityNotFoundException(String.format(COMMON_INFO_NOT_FOUND, commonInfoLanguageInput.getLanguageCode())));

        LanguageEntity language = languageRepository.findByLanguageCode(commonInfoLanguageInput.getLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, commonInfoLanguageInput.getLanguageCode())));


        CommonInfoLanguageEntity commonInfoLanguage = new CommonInfoLanguageEntity();
        commonInfoLanguage.setLabelValue(commonInfoLanguageInput.getLabelValue());
        commonInfoLanguage.setContentValue(commonInfoLanguageInput.getContentValue());
        commonInfoLanguage.setLanguage(language);
        commonInfoLanguage.setCommonInfo(commonInfo);

        commonInfoLanguage = commonInfoLanguageRepository.save(commonInfoLanguage);
        if (!commonInfoLanguageInput.getDetails().isEmpty()) {
            List<CommonInfoLanguageDetailEntity> details = commonInfoLanguageInput.getDetails().stream().map(appMapper::map).toList();
            Long commonInfoLanguageId = commonInfoLanguage.getCommonInfoLanguageId();
            details.forEach(r -> r.setCommonInfoLanguageId(commonInfoLanguageId));
            commonInfoLanguage.getDetails().addAll(details);
            commonInfoLanguage = commonInfoLanguageRepository.save(commonInfoLanguage);
        }
        return commonInfoLanguage;
    }

    private CommonInfoLanguageDto saveCommonInfo(CommonInfoLanguageInput commonInfoLanguageInput) {
        CommonInfoLanguageEntity commonInfoLanguage = verifyExistingOfCommonInfoLanguage(commonInfoLanguageInput);

        if (commonInfoLanguage != null) {
            commonInfoLanguage = updateExistedCommonInfoLanguageEntity(commonInfoLanguageInput, commonInfoLanguage);
        } else {
            commonInfoLanguage = createdNewCommonInfoLanguageEntity(commonInfoLanguageInput);
        }

        return appMapper.map(commonInfoLanguage);
    }

    private CommonInfoLanguageEntity verifyExistingOfCommonInfoLanguage(CommonInfoLanguageInput commonInfoLanguageInput) {
        Specification<CommonInfoLanguageEntity> specification = Specification
                .where(languageCodeEqual(commonInfoLanguageInput.getLanguageCode()))
                .and(commonInfoCodeEqual(commonInfoLanguageInput.getCommonInfoCode()))
                .and(commonInfoTypeEqual(CommonInfoTypeEnum.valueOf(commonInfoLanguageInput.getCommonInfoTypeCode())));
        List<CommonInfoLanguageEntity> list = commonInfoLanguageRepository.findAll(specification);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    private LanguageEntity findLanguageByCode(String languageCode) {
        return languageRepository.findByLanguageCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, languageCode)));
    }

    private CommonInfoTypeEntity findByCode(String code) {
        return commonInfoTypeRepository.findByCommonInfoType(code).orElseThrow(() -> new EntityNotFoundException(""));
    }
}
