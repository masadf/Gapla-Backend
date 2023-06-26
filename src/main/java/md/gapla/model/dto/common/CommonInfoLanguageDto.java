package md.gapla.model.dto.common;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.entity.common.CommonInfoLanguageDetailEntity;
import md.gapla.model.enums.CommonInfoTypeEnum;

import java.util.List;

@Data
public class CommonInfoLanguageDto {
    private Long commonInfoLanguageId;

    private String labelValue;

    private String contentValue;

    private Long commonInfoId;
    private String commonInfoCode;
    private String commonInfoType;
    private List<CommonInfoLanguageDetailDto> details;

//    private LanguageDto language;

}
