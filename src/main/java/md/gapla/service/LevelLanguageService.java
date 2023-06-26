package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDetailDto;
import md.gapla.model.dto.view.LevelLanguageViewDto;
import md.gapla.model.input.LevelLanguageDetailInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LevelLanguageService {

    Page<LevelLanguageViewDto> getLevelLanguageDetailPage(PageParamDto pageParamDto);

    List<LevelLanguageDetailDto> getLevelLanguageDetailList(PageParamDto pageParamDto);

    LevelLanguageDetailDto addLevelLanguageDetail(LevelLanguageDetailInput input);

    LevelLanguageDetailDto updateLevelLanguageDetail(LevelLanguageDetailInput input);
}
