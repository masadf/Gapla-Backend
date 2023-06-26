package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.accountcourse.AccountCourseDto;
import md.gapla.model.entity.account.AccountCourseProgressEntity;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.course.CourseLanguageEntity;
import md.gapla.model.enums.AccountCourseProgressStatusEnum;
import md.gapla.repository.account.AccountCourseProgressRepository;
import md.gapla.service.AccountCourseService;
import md.gapla.service.AccountService;
import md.gapla.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountCourseServiceImpl implements AccountCourseService {

    private final AccountService accountService;

    private final CourseService courseService;


    private final AccountCourseProgressRepository accountCourseProgressRepository;

    @Override
    public List<AccountCourseDto> getCourseByAccount(String token, PageParamDto pageParamDto) {
        String languageCode = pageParamDto.getParams().get("languageCode");
        List<CourseLanguageEntity> list = courseService.findAll(languageCode);
        AccountEntity accountEntity = accountService.getEntityByToken(token);

        List<AccountCourseProgressEntity> proggressList = accountCourseProgressRepository.findByAccountId(accountEntity.getAccountId());

        List<AccountCourseDto> accountCourseDtoList = new ArrayList<>();

        list.forEach(r -> {
            proggressList.forEach(r1 -> {
                if (r.getCourseId().equals(r1.getCourseId())) {
                    AccountCourseDto accountCourseDto = new AccountCourseDto();
                    accountCourseDto.setCourseId(r.getCourseId());
                    accountCourseDto.setLabelValue(r.getLabelValue());
                    accountCourseDto.setContentValue(r.getContentValue());
                    accountCourseDto.setAccountCourseProgressStatus(r1.getAccountCourseProgressStatus());
                    accountCourseDto.setLanguageCode(r.getLanguage().getLanguageCode());
                    accountCourseDtoList.add(accountCourseDto);
                }
            });
        });
        List<Long> allCourses = list.stream().map(CourseLanguageEntity::getCourseId).toList();
        List<Long> allInProcess = accountCourseDtoList.stream().map(AccountCourseDto::getCourseId).toList();
        List<Long> notStarted = allCourses.stream().filter(r -> !allInProcess.contains(r)).toList();

        list.forEach(r -> {
            notStarted.forEach(r1 -> {
                if (r1.equals(r.getCourseId())) {
                    AccountCourseDto accountCourseDto = new AccountCourseDto();
                    accountCourseDto.setCourseId(r.getCourseId());
                    accountCourseDto.setLabelValue(r.getLabelValue());
                    accountCourseDto.setContentValue(r.getContentValue());
                    accountCourseDto.setLanguageCode(r.getLanguage().getLanguageCode());
                    accountCourseDto.setAccountCourseProgressStatus(AccountCourseProgressStatusEnum.NOT_START.getValue());
                    accountCourseDtoList.add(accountCourseDto);
                }
            });
        });
        return accountCourseDtoList;
    }
}
