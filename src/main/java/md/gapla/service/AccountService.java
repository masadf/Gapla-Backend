package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountCheckLevelDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.account.AccountExamCheckLevelDto;
import md.gapla.model.dto.auth.RegisterExtendedRequest;
import md.gapla.model.dto.view.AccountViewDto;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.input.AccountUpdateInput;
import md.gapla.model.input.ExamResultInput;
import md.kobalt.security.auth.AuthenticationRequest;
import md.kobalt.security.auth.AuthenticationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    
    List<AccountDto> filterAccounts(String name, String surname, String email, String telephoneNumber, String role);
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
    
    void addBookmarkToAccount(Long accountId, Long questionId);
    void deleteBookmarkFromAccount(Long accountId, Long questionId);
    List<Long> getBookMarksList(Long accountId);
    
    List<ForumQuestionsEntity> getQuestionsByUser(Long accountId);
	
	List<Long> getDiplomaList(Long accountId);
}
