package md.gapla.controller;

import lombok.AllArgsConstructor;
import lombok.Value;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDetailDto;
import md.gapla.model.dto.view.LevelLanguageViewDto;
import md.gapla.model.input.LevelLanguageDetailInput;
import md.gapla.service.LevelLanguageService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "level-language")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/level-language")
@AllArgsConstructor
@CrossOrigin
public class LevelLanguageController {

    private final LevelLanguageService levelLanguageService;
    @PostMapping(value = "/page")
    public ResponseEntity<Page<LevelLanguageViewDto>> getLevelLanguageDetailDtoPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(levelLanguageService.getLevelLanguageDetailPage(pageParamDto));
    }

    @PostMapping
    public ResponseEntity<LevelLanguageDetailDto> addLevelLanguageDetail(@RequestBody LevelLanguageDetailInput input) {
        return ResponseEntity.ok(levelLanguageService.addLevelLanguageDetail(input));
    }
    @PutMapping
    public ResponseEntity<LevelLanguageDetailDto> updateLevelLanguageDetail(@RequestBody LevelLanguageDetailInput input) {
        return ResponseEntity.ok(levelLanguageService.updateLevelLanguageDetail(input));
    }
}
