package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.common.CommonInfoTypeLanguageDto;
import md.gapla.service.CommonInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "common-info-type")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/common-info-type")
@AllArgsConstructor
@CrossOrigin
public class CommonInfoTypeController {
    private final CommonInfoService commonInfoService;


    //    @Operation(description = "Get all accounts from organisation", tags = {"Account"})
    @GetMapping(value = "/list")
    public ResponseEntity<List<CommonInfoTypeLanguageDto>> getCommonInfoTypeList(@RequestHeader("languageCode") String languageCode) {
        return ResponseEntity.ok(commonInfoService.findAllCommonInfoTypeByLanguageCode(languageCode));
    }
}
