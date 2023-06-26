package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestTimeTypeDto;
import md.gapla.model.dto.test.TestTimeTypeLanguageDto;
import md.gapla.model.dto.view.TimeTypeViewDto;
import md.gapla.model.input.test.TestTimeTypeLanguageInput;
import md.gapla.service.TimeTestTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Time tet type")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/time-test-type")
@AllArgsConstructor
@CrossOrigin
public class TimeTestTypeController {
    private final TimeTestTypeService timeTestTypeService;

    //    @Operation(description = "Get all accounts from organisation", tags = {"Account"})
    @PostMapping(value = "/list")
    public ResponseEntity<Page<TimeTypeViewDto>> getAccountsList(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(timeTestTypeService.findAllTestTimeType(pageParamDto));
    }

    @PostMapping(value = "/list/{id}")
    public ResponseEntity<Page<TestTimeTypeLanguageDto>> getAccountsList(@PathVariable("id") Long id,
                                                                         @RequestHeader("languageCode") String languageCode,
                                                                         @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(timeTestTypeService.findAllTestTimeTypeLanguageAndId(id, languageCode, pageParamDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TestTimeTypeLanguageDto> getTestTimeTypeLanguage(@PathVariable("id") Long id,
                                                                           @RequestHeader("languageCode") String languageCode) {
        return ResponseEntity.ok(timeTestTypeService.finTestTimeTypeLanguageById(languageCode, id));
    }
    @PostMapping(value = "/{id}")
    public ResponseEntity<TestTimeTypeLanguageDto> saveTestTimeTypeLanguage(@PathVariable("id") Long id,
                                                                            @RequestBody TestTimeTypeLanguageInput testTimeTypeLanguageInput) {
        return ResponseEntity.ok(timeTestTypeService.addNewTestTimeTypeLanguage(id,testTimeTypeLanguageInput));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TestTimeTypeLanguageDto> updateTestTimeTypeLanguage(@PathVariable("id") Long id,
                                                                            @RequestBody TestTimeTypeLanguageInput testTimeTypeLanguageInput) {
        return ResponseEntity.ok(timeTestTypeService.updateExistedTestTimeTypeLanguage(id,testTimeTypeLanguageInput));
    }
}
