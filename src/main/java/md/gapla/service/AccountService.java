package md.gapla.service;

//import md.gapla.model.auth.AuthenticationRequest;
//import md.gapla.model.auth.AuthenticationResponse;
//import md.gapla.model.auth.RegisterRequest;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountCheckLevelDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.account.AccountExamCheckLevelDto;
import md.gapla.model.dto.auth.RegisterExtendedRequest;
import md.gapla.model.dto.view.AccountViewDto;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.input.AccountUpdateInput;
import md.gapla.model.input.ExamResultInput;
import md.kobalt.security.auth.AuthenticationRequest;
import md.kobalt.security.auth.AuthenticationResponse;
import org.springframework.data.domain.Page;

public interface AccountService {

    Page<AccountDto> accountsList(PageParamDto pageParamDto);
    Page<AccountViewDto> accountsViewList(PageParamDto pageParamDto);

    void addAccount(AccountDto input);

    void updateAccount(AccountDto input);

    AuthenticationResponse register(RegisterExtendedRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AccountDto getMyProfile(String token);
    AccountEntity getEntityByToken(String token);
    AccountEntity get(Long accountId);
    AccountViewDto getAccountViewDto(Long accountId);
    AccountDto updateMyAccount(AccountUpdateInput input, String token);

    AccountCheckLevelDto setResults(AccountCheckLevelDto input);
    AccountExamCheckLevelDto setExamResults(ExamResultInput input);
}
