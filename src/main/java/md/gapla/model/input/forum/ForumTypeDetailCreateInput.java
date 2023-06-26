package md.gapla.model.input.forum;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;

import java.util.Date;

@Data
public class ForumTypeDetailCreateInput {

    private Long forumTypeDetailId;
    private Long forumTypeId;
    private String value;

    private String languageCode;

}
