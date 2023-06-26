package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestQuestionTypeLanguageDto;
import md.gapla.model.input.test.TestQuestionTypeLanguageInput;
import md.gapla.service.TestQuestionTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "test-question-type")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/test-question-type")
@AllArgsConstructor
@CrossOrigin
public class TestQuestionTypeController {
    private final TestQuestionTypeService testQuestionTypeService;


    @PostMapping(value = "/list")
    public ResponseEntity<Page<TestQuestionTypeLanguageDto>> getTestQuestionTypePage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(testQuestionTypeService.getTestQuestionTypePage(pageParamDto));
    }

    @GetMapping(value = "/list/{testQuestionTypeId}")
    public ResponseEntity<List<TestQuestionTypeLanguageDto>> getTestQuestionTypeList(@RequestHeader("languageCode") String languageCode,
                                                                                     @PathVariable("testQuestionTypeId") Long testQuestionTypeId) {
        return ResponseEntity.ok(testQuestionTypeService.getTestQuestionTypeList(languageCode, testQuestionTypeId));
    }

    @PostMapping(value = "/{testQuestionTypeId}")
    public ResponseEntity<TestQuestionTypeLanguageDto> addTestQuestionType(@PathVariable("testQuestionTypeId") Long testQuestionTypeId,
                                                                           @RequestBody TestQuestionTypeLanguageInput testQuestionTypeLanguageInput) {
        return ResponseEntity.ok(testQuestionTypeService.addTestQuestionType(testQuestionTypeId, testQuestionTypeLanguageInput));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TestQuestionTypeLanguageDto> updateTestQuestionType(@PathVariable Long testQuestionTypeId,
                                                                              @RequestBody TestQuestionTypeLanguageInput testQuestionTypeLanguageInput) {
        return ResponseEntity.ok(testQuestionTypeService.updateTestQuestionType(testQuestionTypeId, testQuestionTypeLanguageInput));
    }
}
