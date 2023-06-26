package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.dto.test.TestContentDto;
import md.gapla.model.dto.test.TestDetailDto;
import md.gapla.model.dto.test.TestDto;
import md.gapla.model.dto.test.TestQuestionTypeTestTimeTypeDetailDto;
import md.gapla.model.input.test.TestInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestService {

    Page<TestDto> findTestPage(PageParamDto pageParamDto);

    void deleteTestByTestId(Long testId);

    TestDto addTest(TestInput testInput);

    TestDto updateTest(TestInput testInput);

    TestContentDto findById(Long testId);
    ExamDto findExamById(Long examId);


    ExamDto findByCourseId(String languageCode, Long testId);

    TestContentDto getTestByTestTimeAndLanguageCode(Long testTimeId, String languageCode);
    List<TestQuestionTypeTestTimeTypeDetailDto> getTestDetailInfo(Long testTimeId, String languageCode);
}
