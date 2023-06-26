package md.gapla.model.dto.forum;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;

import java.util.Date;

@Data
public class ForumDto {
    private Long forumId;

    private String forumName;

    private AccountDto account;


    private ForumTypeDto forumType;

    private Date createdtime;
}
