package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.dto.test.TestContentDto;
import md.gapla.model.dto.test.TestDetailDto;
import md.gapla.model.dto.test.TestDto;
import md.gapla.model.dto.test.TestQuestionTypeTestTimeTypeDetailDto;
import md.gapla.model.input.ExamResultInput;
import md.gapla.model.input.test.TestInput;
import md.gapla.service.TestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "test")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/test")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Test", description = "Работа с тестами.")
public class TestController {

    private final TestService testService;
    
    @Operation(summary = "Получение всех тестов.")
    @PostMapping(value = "/page")
    public ResponseEntity<Page<TestDto>> getTestPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(testService.findTestPage(pageParamDto));
    }
    
    @Operation(summary = "Создание теста.")
    @PostMapping
    public ResponseEntity<TestDto> addTest(@RequestBody TestInput testInput) {
        return ResponseEntity.ok(testService.addTest(testInput));
    }
    
    @Operation(summary = "Редактирование теста.")
    @PutMapping
    public ResponseEntity<TestDto> updateTest(@RequestBody TestInput testInput) {
        return ResponseEntity.ok(testService.updateTest(testInput));
    }
    
    @Operation(summary = "Удаление теста по id.")
    @DeleteMapping(value = "/{testId}")
    public ResponseEntity<Void> deleteTestById(@PathVariable("testId") Long testId) {
        testService.deleteTestByTestId(testId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{testId}")
    public ResponseEntity<TestContentDto> getTestPage(@PathVariable("testId") Long testId) {
        return ResponseEntity.ok(testService.findById(testId));
    }
    @GetMapping(value = "/exam/{testId}")
    public ResponseEntity<ExamDto> getExamTest(@PathVariable("testId") Long testId) {
        return ResponseEntity.ok(testService.findExamById(testId));
    }



    @GetMapping(value = "/test-time/info/{timeTestId}")
    public ResponseEntity<List<TestQuestionTypeTestTimeTypeDetailDto>> getTestDetailInfo(@RequestHeader("languageCode") String languageCode,
                                                                                         @PathVariable("timeTestId") Long timeTestId) {
        return ResponseEntity.ok(testService.getTestDetailInfo(timeTestId, languageCode));
    }

    @GetMapping(value = "/test-time/{timeTestId}")
    public ResponseEntity<TestContentDto> getTestByTestTime(@RequestHeader("languageCode") String languageCode,
                                                            @PathVariable("timeTestId") Long timeTestId) {
        return ResponseEntity.ok(testService.getTestByTestTimeAndLanguageCode(timeTestId, languageCode));
    }

    @GetMapping(value = "/course/{courseId}")
    public ResponseEntity<ExamDto> getTestByCourseId(@RequestHeader("languageCode") String languageCode, @PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(testService.findByCourseId(languageCode, courseId));
    }



}
