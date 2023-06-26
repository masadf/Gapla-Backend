package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestQuestionTypeLanguageDto;
import md.gapla.model.input.test.TestQuestionTypeLanguageInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestQuestionTypeService {

    TestQuestionTypeLanguageDto addTestQuestionType(Long testQuestionTypeId,TestQuestionTypeLanguageInput testQuestionTypeLanguageInput);

    TestQuestionTypeLanguageDto updateTestQuestionType(Long testQuestionTypeId,TestQuestionTypeLanguageInput testQuestionTypeLanguageInput);

    Page<TestQuestionTypeLanguageDto> getTestQuestionTypePage(PageParamDto pageParamDto);
    List<TestQuestionTypeLanguageDto> getTestQuestionTypeList(String languageCode, Long testQuestionTypeId);
}
