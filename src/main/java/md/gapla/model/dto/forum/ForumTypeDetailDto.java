package md.gapla.model.dto.forum;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;

import java.util.Date;

@Data
public class ForumTypeDetailDto {
    private Long forumTypeDetailId;

    private Long forumTypeId;

    private String value;

    private LanguageDto language;

    private Date createdTime;
}
