package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestTimeTypeDto;
import md.gapla.model.dto.test.TestTimeTypeLanguageDto;
import md.gapla.model.dto.view.TimeTypeViewDto;
import md.gapla.model.entity.test.TestTimeTypeEntity;
import md.gapla.model.entity.test.TestTimeTypeLanguageEntity;
import md.gapla.model.input.test.TestTimeTypeLanguageInput;
import org.springframework.data.domain.Page;

public interface TimeTestTypeService {

    Page<TimeTypeViewDto> findAllTestTimeType(PageParamDto pageParamDto);
    Page<TestTimeTypeLanguageDto> findAllTestTimeTypeLanguageAndId(Long  id,String languageCode, PageParamDto pageParamDto);

    TestTimeTypeLanguageDto finTestTimeTypeLanguageById(String languageCode, Long timeTypeTestId);

    TestTimeTypeLanguageDto addNewTestTimeTypeLanguage(Long id,TestTimeTypeLanguageInput testTimeTypeLanguageInput);

    TestTimeTypeLanguageDto updateExistedTestTimeTypeLanguage(Long id,TestTimeTypeLanguageInput testTimeTypeLanguageDto);

}
