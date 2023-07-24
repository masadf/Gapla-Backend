package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountCheckLevelDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.account.AccountExamCheckLevelDto;
import md.gapla.model.dto.view.AccountViewDto;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.input.AccountUpdateInput;
import md.gapla.model.input.ExamResultInput;
import md.gapla.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "account")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/account")
@AllArgsConstructor
@CrossOrigin
public class AccountController {
    private final AccountService accountService;


    //    @Operation(description = "Get all accounts from organisation", tags = {"Account"})
    @PostMapping(value = "/list")
    public ResponseEntity<Page<AccountDto>> getAccountsList(@RequestBody PageParamDto pageParamDto) {//
        return ResponseEntity.ok(accountService.accountsList(pageParamDto));
    }

    @PostMapping(value = "/list/view")
    public ResponseEntity<Page<AccountViewDto>> accountsViewList(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(accountService.accountsViewList(pageParamDto));
    }

    @PostMapping
    public ResponseEntity<Void> saveAccount(@RequestBody AccountDto input) {
        accountService.addAccount(input);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateAccount(@RequestBody AccountDto input) {
        accountService.updateAccount(input);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountViewDto> getAccountById(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(accountService.getAccountViewDto(accountId));
    }

    @GetMapping(value = "/me")
    public ResponseEntity<AccountDto> getMyProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(accountService.getMyProfile(token));
    }

    @PutMapping(value = "/me")
    public ResponseEntity<AccountDto> updateMyAccount(@RequestHeader("Authorization") String token,
                                                      @RequestBody AccountUpdateInput input) {
        return ResponseEntity.ok(accountService.updateMyAccount(input, token));
    }

    @PostMapping(value = "/set-result")
    public ResponseEntity<AccountCheckLevelDto> setResults(@RequestBody AccountCheckLevelDto input) {
        return ResponseEntity.ok(accountService.setResults(input));
    }

    @PostMapping(value = "/exam/set-result")
    public ResponseEntity<AccountExamCheckLevelDto> setExamResult(@RequestBody ExamResultInput input) {
        return ResponseEntity.ok(accountService.setExamResults(input));
    }
    
    @PostMapping("/{accountId}/bookmarks/{questionId}")
    public ResponseEntity<String> addBookmarkToAccount(@PathVariable Long accountId,
                                                       @PathVariable Long questionId){
        accountService.addBookmarkToAccount(accountId, questionId);
        return ResponseEntity.ok("Bookmark added successfully.");
    }
    
    @PostMapping("/{accountId}/questions")
    public ResponseEntity<List<ForumQuestionsEntity>> getQuestionsByUser(@PathVariable Long accountId){
        List<ForumQuestionsEntity> questions = accountService.getQuestionsByUser(accountId);
        return ResponseEntity.ok(questions);
    }
}
