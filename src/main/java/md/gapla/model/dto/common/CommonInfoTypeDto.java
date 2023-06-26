package md.gapla.model.dto.common;

import lombok.Data;
import md.gapla.model.enums.CommonInfoTypeEnum;

@Data
public class CommonInfoTypeDto {
    private Long commonInfoId;

    private CommonInfoTypeEnum commonInfoType;
}
