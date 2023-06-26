package md.gapla.model.dto.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class TimeTypeViewDto {
    private Long testTimeTypeLanguageId;


    private Long testTimeTypeId;

    private String testTime;

    private String languageCode;

    private String value;

}
