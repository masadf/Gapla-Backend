package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.PageParamDto;
import md.gapla.repository.LanguageRepository;
import md.gapla.service.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final AppMapper appMapper;

    @Override
    public List<LanguageDto> findAll() {
        return languageRepository.findAll().stream().map(appMapper::map).toList();
    }

    @Override
    public Page<LanguageDto> findAll(PageParamDto pageParamDto) {
        return languageRepository.findAll(pageParamDto.getPageRequest()).map(appMapper::map);
    }
}
