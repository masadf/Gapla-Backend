package md.gapla.model.dto.forum;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;

import java.util.Date;
import java.util.List;

@Data
public class ForumQuestionsCommentsDto {
    private Long forumQuestionsCommentId;

//    private ForumQuestionsDto forumQuestions;

    private String comment;

    private AccountDto account;

    private Date createdTime;

    private List<ForumQuestionsCommentsImageDto> images;
}
