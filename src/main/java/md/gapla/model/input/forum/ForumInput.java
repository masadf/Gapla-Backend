package md.gapla.model.input.forum;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.forum.ForumTypeDto;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.Date;

@Data
public class ForumInput {
    private Long forumId;
    private String forumName;
    private Long forumTypeDetailId;
    private String languageCode;

    private ObjectStatusEnum status;

}
