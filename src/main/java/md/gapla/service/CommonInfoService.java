package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.common.CommonInfoDto;
import md.gapla.model.dto.common.CommonInfoLanguageDto;
import md.gapla.model.dto.common.CommonInfoTypeDto;
import md.gapla.model.dto.common.CommonInfoTypeLanguageDto;
import md.gapla.model.input.CommonInfoLanguageInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonInfoService {

    List<CommonInfoTypeLanguageDto> findAllCommonInfoTypeByLanguageCode(String languageCode);
    
    Page<CommonInfoDto> findAll(PageParamDto pageParamDto, String languageCode);

    CommonInfoLanguageDto updateCommonInfo(CommonInfoLanguageInput commonInfoDto);

    CommonInfoLanguageDto addCommonInfo(CommonInfoLanguageInput commonInfoDto);
}
