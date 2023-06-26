package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestQuestionDto;
import md.gapla.model.input.test.TestQuestionInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestQuestionService {

    TestQuestionDto addTestQuestion(Long testQuestionTypeId, TestQuestionInput testQuestionInput);

    TestQuestionDto updateTestQuestion(Long testQuestionTypeId, TestQuestionInput testQuestionInput);

    Page<TestQuestionDto> getTestQuestionPage(PageParamDto pageParamDto);

    List<TestQuestionDto> getTestQuestionList(String languageCode, PageParamDto pageParamDto);

    void deleteTestQuestionByTestQuestionId(Long testQuestionId);
}
