package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.accountcourse.AccountCourseDto;

import java.util.List;

public interface AccountCourseService {

    List<AccountCourseDto> getCourseByAccount(String token, PageParamDto pageParamDto);
}
