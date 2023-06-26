package md.gapla.model.dto.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class MeetingTypeViewDto {
    private Long meetingTypeLanguageId;
    private Long meetingTypeId;

    private String value;

    private String type;

    private String languageCode;

}
