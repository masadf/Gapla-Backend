package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.service.AccountService;
import md.gapla.service.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "language")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/language")
@AllArgsConstructor
@CrossOrigin
public class LanguageController {
    private final LanguageService languageService;


    //    @Operation(description = "Get all accounts from organisation", tags = {"Account"})
    @PostMapping(value = "/page")
    public ResponseEntity<Page<LanguageDto>> getAllLanguages(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(languageService.findAll(pageParamDto));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        return ResponseEntity.ok(languageService.findAll());
    }
}
