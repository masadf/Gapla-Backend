package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.auth.RegisterExtendedRequest;
import md.gapla.service.AccountService;
import md.kobalt.security.auth.AuthenticationRequest;
import md.kobalt.security.auth.AuthenticationResponse;
import md.kobalt.security.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "auth")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterExtendedRequest request) {
        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(accountService.authenticate(request));
    }


//    //    @Operation(description = "Get all accounts from organisation", tags = {"Account"})
//    @PostMapping(value = "/list")
//    public ResponseEntity<Page<AccountDto>> getAccountsList(@RequestBody PageParamDto pageParamDto) {
//        return ResponseEntity.ok(accountService.accountsList(pageParamDto));
//    }
}
