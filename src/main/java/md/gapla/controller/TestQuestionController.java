package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestQuestionDto;
import md.gapla.model.input.test.TestQuestionInput;
import md.gapla.service.TestQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "test-question")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/test-question")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Test Question", description = "Работа с вопросами тестов")
public class TestQuestionController {
    private final TestQuestionService testQuestionService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<TestQuestionDto>> getTestQuestionPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(testQuestionService.getTestQuestionPage(pageParamDto));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<TestQuestionDto>> getTestQuestionList(@RequestHeader(value = "languageCode", defaultValue = "", required = false) String languageCode,
                                                                     @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(testQuestionService.getTestQuestionList(languageCode, pageParamDto));
    }
    
    @Operation(summary = "Получение вопроса к тесту по id вопроса.")
    @GetMapping(value = "/{testQuestionId}")
    public ResponseEntity<TestQuestionDto> getTestQuestion(@PathVariable("testQuestionId") Long testQuestionId){
        return ResponseEntity.ok(testQuestionService.getTestQuestion(testQuestionId));
    }
    
    @PostMapping(value = "/{testQuestionTypeId}")
    public ResponseEntity<TestQuestionDto> addTestQuestion(@PathVariable("testQuestionTypeId") Long testQuestionTypeId,
                                                           @RequestBody TestQuestionInput testQuestionInput) {
        return ResponseEntity.ok(testQuestionService.addTestQuestion(testQuestionTypeId, testQuestionInput));
    }

    @PutMapping(value = "/{testQuestionTypeId}")
    public ResponseEntity<TestQuestionDto> updateTestQuestion(@PathVariable("testQuestionTypeId") Long testQuestionTypeId,
                                                              @RequestBody TestQuestionInput testQuestionInput) {
        return ResponseEntity.ok(testQuestionService.updateTestQuestion(testQuestionTypeId, testQuestionInput));
    }

    @DeleteMapping("/{testQuestionId}")
    public ResponseEntity<Void> deleteTestQuestionById(@PathVariable("testQuestionId") Long testQuestionId) {
        testQuestionService.deleteTestQuestionByTestQuestionId(testQuestionId);
        return ResponseEntity.ok().build();
    }
}
