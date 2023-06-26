package md.gapla.service;

import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.PageParamDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LanguageService {

    List<LanguageDto> findAll();
    Page<LanguageDto> findAll(PageParamDto pageParamDto);
}
