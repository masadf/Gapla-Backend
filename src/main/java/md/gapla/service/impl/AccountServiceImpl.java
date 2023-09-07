package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.account.AccountCheckLevelDto;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.account.AccountExamCheckLevelDto;
import md.gapla.model.dto.auth.RegisterExtendedRequest;
import md.gapla.model.dto.view.AccountViewDto;
import md.gapla.model.entity.account.AccountCheckLevelEntity;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.account.AccountRoleEntity;
import md.gapla.model.entity.courseexam.CourseExamEntity;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.entity.view.AccountViewEntity;
import md.gapla.model.enums.AccountRoleEnum;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.AccountUpdateInput;
import md.gapla.model.input.ExamResultInput;
import md.gapla.repository.account.AccountCheckLevelRepository;
import md.gapla.repository.account.AccountRepository;
import md.gapla.repository.account.AccountRoleRepository;
import md.gapla.repository.course.CourseExamRepository;
import md.gapla.repository.forum.ForumQuestionsRepository;
import md.gapla.repository.specification.filters.AccountViewSpec;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.view.AccountViewRepository;
import md.gapla.service.AccountService;
import md.kobalt.security.auth.AuthenticationRequest;
import md.kobalt.security.auth.AuthenticationResponse;
import md.kobalt.security.config.JwtService;
import md.kobalt.security.user.JwtUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static md.gapla.repository.specification.AccountSpec.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AppMapper appMapper;
    private final AccountRepository accountRepository;

    private final AccountRoleRepository accountRoleRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AccountCheckLevelRepository accountCheckLevelRepository;


    private final AccountViewRepository accountViewRepository;

    private final CourseExamRepository courseExamRepository;
    
    private final ForumQuestionsRepository forumQuestionsRepository;
//    private final AccountRoleRepository accountRoleRepository;
//    private final AccountCourseProgressRepository accountCourseProgressRepository;
//    private final AccountCourseLessonProgressEntity accountCourseLessonProgressEntity;
    
    @Override
    public List<AccountDto> filterAccounts(String name, String surname, String email, String telephoneNumber, String role) {
        List<AccountEntity> filteredAccounts = accountRepository.findAccountsByFilter(name, surname, email, telephoneNumber, role);
        return filteredAccounts.stream().map(appMapper::map).toList();
    }
    
    @Override
    public Page<AccountDto> accountsList(PageParamDto pageParamDto) {
        Map<String, String> params = isEmptyParams(pageParamDto.getParams());
        String fam = params.get("fam");
        String im = params.get("im");
        String ot = params.get("ot");
        String accountRole = params.get("accountRole");

        Specification<AccountEntity> specification = Specification
                .where(StringUtils.hasText(accountRole) ? accountRoleEqual(AccountRoleEnum.valueOf(accountRole)) : null)
                .and(StringUtils.hasText(fam) ? likeFam(fam.toUpperCase()) : null)
                .and(StringUtils.hasText(im) ? likeIm(im.toUpperCase()) : null)
                .and(StringUtils.hasText(ot) ? likeOt(ot.toUpperCase()) : null);


        Page<AccountEntity> list = accountRepository.findAll(specification, pageParamDto.getPageRequest());

        return list.map(appMapper::map);
    }

    @Override
    public Page<AccountViewDto> accountsViewList(PageParamDto pageParamDto) {
        Specification<AccountViewEntity> masterSpec = null;
        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new AccountViewSpec(filterCriteria));
        }
        Page<AccountViewEntity> pages = accountViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());
        return pages.map(appMapper::map);
    }

    @Override
    public void addAccount(AccountDto input) {
        Optional<AccountEntity> existed = finByEmail(input.getEmail());

        if (existed.isPresent()) {
            throw new InvalidRequestException("Account already exists.");
        }

        AccountEntity accountEntity = appMapper.map(input);
        accountEntity.setStatus(ObjectStatusEnum.ENABLE.getValue());
        accountRepository.save(accountEntity);
    }

    @Override
    @Transactional
    public void updateAccount(AccountDto input) {
        AccountEntity existed = accountRepository.findById(input.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Acoount with account id = " + input.getAccountId() + " not found."));

        if (!input.getEmail().equals(existed.getEmail())) {
            Optional<AccountEntity> existedWIthOtherEmail = finByEmail(input.getEmail());
            if (existedWIthOtherEmail.isPresent()) {
                throw new InvalidRequestException("Email already exists.");
            }
        }

        existed.setIm(input.getIm());
        existed.setFam(input.getFam());
        existed.setOt(input.getOt());
        existed.setEmail(input.getEmail());
        existed.setPhoneNumber(input.getPhoneNumber());
        existed.setStatus(input.getStatus());

        accountRepository.save(existed);
    }

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterExtendedRequest request) {

        var existedUser = accountRepository.findByEmail(request.getEmail());
        AccountRoleEntity accountRoleEntity = accountRoleRepository.findById(2l).orElseThrow(() -> new EntityNotFoundException("Not found role."));
        if (existedUser.isPresent()) {
            throw new InvalidRequestException("Account with this email already exists.");
        }

        String encodedPass = passwordEncoder.encode(request.getPassword());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(request.getEmail());
        accountEntity.setPassword(encodedPass);
        accountEntity.setOt(request.getOt());
        accountEntity.setFam(request.getFam());
        accountEntity.setIm(request.getIm());
        accountEntity.setGender(request.getGender());
        accountEntity.setTown(request.getTown());
        accountEntity.setRoles(List.of(accountRoleEntity));
        accountEntity = accountRepository.save(accountEntity);

        var user = JwtUserDetails.builder()
                .id(Integer.parseInt(accountEntity.getAccountId().toString()))
                .fam(accountEntity.getFam())
                .im(accountEntity.getIm())
                .ot(accountEntity.getOt())
                .email(accountEntity.getEmail())
                .password(encodedPass)
                .role(accountRoleEntity.getAccountRoleName().getValue())
                .build();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = accountRepository.findByEmail(request.getEmail())
                .orElseThrow();

        JwtUserDetails jwtUserDetails = JwtUserDetails.builder()
                .email(user.getEmail())
                .id(Integer.parseInt(user.getAccountId().toString()))
                .password(user.getPassword())
                .build();

        var jwtToken = jwtService.generateToken(jwtUserDetails);
        user.setLastVisit(LocalDateTime.now());
        accountRepository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AccountDto getMyProfile(String token) {
        return appMapper.map(getEntityByToken(token));
    }

    @Override
    public AccountEntity getEntityByToken(String token) {
        String email = jwtService.extractUsername(token);

        return accountRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Not found account"));
    }

    @Override
    public AccountEntity get(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Not found account"));
    }

    @Override
    public AccountViewDto getAccountViewDto(Long accountId) {
        return appMapper.map(accountViewRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Not found account by id")));
    }

    @Override
    public AccountDto updateMyAccount(AccountUpdateInput input, String token) {

        AccountEntity accountEntity = getEntityByToken(token);
        if (!input.getEmail().equals(accountEntity.getEmail())) {
            Optional<AccountEntity> existedUserWithEmail = accountRepository.findByEmail(input.getEmail());
            if (existedUserWithEmail.isPresent()) {
                throw new InvalidRequestException("Email is existed");
            }
        }
        accountEntity.setEmail(input.getEmail());
        accountEntity.setUserName(input.getUserName());
        accountEntity.setOt(input.getOt());
        accountEntity.setFam(input.getFam());
        accountEntity.setIm(input.getIm());
        accountEntity.setGender(input.getGender());
        accountEntity.setTown(input.getTown());
        accountEntity.setGender(input.getGender());
        accountEntity.setCountry(input.getCountry());
        accountEntity.setPhoneNumber(input.getPhoneNumber());
        accountEntity.setBirthdate(formatToDate(input.getBirthdate()));
        if (input.getPassword() != null && !input.getPassword().isEmpty()) {
            accountEntity.setPassword(passwordEncoder.encode(input.getPassword()));
        }

        accountEntity = accountRepository.save(accountEntity);
        AccountDto dto = appMapper.map(accountEntity);
        dto.setToken(getToken(accountEntity).getToken());
        return dto;
    }

    @Override
    public AccountCheckLevelDto setResults(AccountCheckLevelDto input) {
        AccountEntity accountEntity = accountRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Not found user by email"));
        AccountCheckLevelEntity accountCheckLevelEntity = new AccountCheckLevelEntity();
        accountCheckLevelEntity.setAccountId(accountCheckLevelEntity.getAccountId());
        accountCheckLevelEntity.setListening(input.getListening());
        accountCheckLevelEntity.setReading(input.getReading());
        accountCheckLevelEntity.setTotal(input.getTotal());
        accountCheckLevelEntity = accountCheckLevelRepository.save(accountCheckLevelEntity);

        AccountCheckLevelDto accountCheckLevelDto = appMapper.map(accountCheckLevelEntity);
        accountCheckLevelDto.setEmail(accountEntity.getEmail());
        return accountCheckLevelDto;
    }

    @Override
    @Transactional//Needed
    public AccountExamCheckLevelDto setExamResults(ExamResultInput input) {
        AccountEntity accountEntity = accountRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Not found user by email"));

        AccountCheckLevelEntity accountCheckLevelEntity = new AccountCheckLevelEntity();

        CourseExamEntity courseExamEntity = courseExamRepository.findByExamIdAndCourseId(input.getExamId(), input.getCourseId());

        if (courseExamEntity == null) {
            throw new EntityNotFoundException("Not found course exam id");
        }

        accountCheckLevelEntity.getCourseExamList().add(courseExamEntity);
        accountCheckLevelEntity.setAccountId(accountEntity.getAccountId());
        accountCheckLevelEntity.setListening(input.getListening());
        accountCheckLevelEntity.setReading(input.getReading());
        accountCheckLevelEntity.setTotal(input.getTotal());
        accountCheckLevelEntity = accountCheckLevelRepository.save(accountCheckLevelEntity);

        AccountExamCheckLevelDto accountCheckLevelDto = appMapper.mapToExam(accountCheckLevelEntity);
        accountCheckLevelDto.setEmail(accountEntity.getEmail());
        return accountCheckLevelDto;
    }
    
    @Override
    public void addBookmarkToAccount(Long accountId, Long questionId){
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with id = " + accountId + " not found"));
        ForumQuestionsEntity question = forumQuestionsRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question with id = " + questionId + " not found"));
        account.getBookmarkedQuestions().add(question);
        accountRepository.save(account);
    }
    
    
    public List<ForumQuestionsEntity> getQuestionsByUser(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with id " + accountId + " not found"));
        return account.getQuestions();
    }

    private Map<String, String> isEmptyParams(Map<String, String> params) {
        if (params.isEmpty()) {
            return new HashMap<>();
        }
        return params;
    }

    private Optional<AccountEntity> finByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    private AuthenticationResponse getToken(AccountEntity accountEntity) {
        JwtUserDetails jwtUserDetails = JwtUserDetails.builder()
                .email(accountEntity.getEmail())
                .id(Integer.parseInt(accountEntity.getAccountId().toString()))
                .password(accountEntity.getPassword())
                .build();

        var jwtToken = jwtService.generateToken(jwtUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private Date formatToDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
