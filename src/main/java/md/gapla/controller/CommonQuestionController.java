package md.gapla.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController(value = "common")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/common")
@AllArgsConstructor
@CrossOrigin
public class CommonQuestionController {

//    private final CommonService commonService;
//
//    @GetMapping(value = "/by-module/{moduleCode}")
//    public ResponseEntity<Page<AccountDto>> getAccountsList(@PathVariable("moduleCode") String moduleCode) {
//        return ResponseEntity.ok(accountService.accountsList(pageParamDto));
//    }
}
