package md.gapla.model.dto.forum;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;

import java.util.Date;
import java.util.List;

@Data
public class ForumQuestionsDto {
    private Long forumQuestionId;


    private String forumQuestionLabel;

    private String forumQuestionContent;


    private AccountDto account;

    private Date createdTime;

    private List<ForumQuestionsImageDto> images;
}
