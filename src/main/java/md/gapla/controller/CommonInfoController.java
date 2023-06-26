package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.common.CommonInfoDto;
import md.gapla.model.dto.common.CommonInfoLanguageDto;
import md.gapla.model.input.CommonInfoLanguageInput;
import md.gapla.service.CommonInfoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "common-info")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/common-info")
@AllArgsConstructor
@CrossOrigin
public class CommonInfoController {

    private final CommonInfoService commonInfoService;

    @GetMapping(value = "/by-module/{moduleCode}")
    public ResponseEntity<List<CommonInfoLanguageDto>> findAll(@PathVariable("moduleCode") String moduleCode, @RequestHeader("languageCode") String languageCode) {
        return ResponseEntity.ok(commonInfoService.findByModule(moduleCode, languageCode));
    }

    @PostMapping(value = "/by-module/{moduleCode}")
    public ResponseEntity<Page<CommonInfoLanguageDto>> findAllForPage(@PathVariable("moduleCode") String moduleCode,
                                                                      @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(commonInfoService.findByModule(moduleCode, pageParamDto));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<Page<CommonInfoDto>> findAll(@RequestBody PageParamDto pageParamDto, @RequestHeader("languageCode") String languageCode) {
        return ResponseEntity.ok(commonInfoService.findAll(pageParamDto, languageCode));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<CommonInfoLanguageDto> addCommonInfo(@RequestBody CommonInfoLanguageInput commonInfoDto) {
        return ResponseEntity.ok(commonInfoService.addCommonInfo(commonInfoDto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<CommonInfoLanguageDto> updateCommonInfo(@RequestBody CommonInfoLanguageInput commonInfoDto) {
        return ResponseEntity.ok(commonInfoService.updateCommonInfo(commonInfoDto));
    }


}
