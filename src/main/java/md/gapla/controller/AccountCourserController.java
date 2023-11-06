package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.accountcourse.AccountCourseDto;
import md.gapla.service.AccountCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "accountCourse")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/account-course")
@AllArgsConstructor
@CrossOrigin
public class AccountCourserController {

    private final AccountCourseService accountCourseService;

    @PostMapping(value = "/progress")
    public ResponseEntity<List<AccountCourseDto>> getAccountsList(@RequestHeader("AuthorizationToken") String token,
                                                                  @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(accountCourseService.getCourseByAccount(token, pageParamDto));
    }
}
