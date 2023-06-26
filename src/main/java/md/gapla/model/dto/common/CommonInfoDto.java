package md.gapla.model.dto.common;

import lombok.Data;
import md.gapla.model.enums.CommonInfoTypeEnum;

@Data
public class CommonInfoDto {
    private Long commonInfoId;
    private String commonInfoCode;
    private String value;

    private CommonInfoTypeEnum commonInfoType;
}
