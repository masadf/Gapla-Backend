package md.gapla.model.input.forum;

import lombok.Data;
import md.gapla.model.dto.account.AccountDto;
import md.gapla.model.dto.forum.ForumQuestionsCommentsImageDto;
import md.gapla.model.dto.forum.ForumQuestionsDto;

import java.util.Date;
import java.util.List;

@Data
public class ForumQuestionsCommentsInput {

    private Long forumQuestionId;

    private String comment;

    private  Long fromReplyId;

    private List<ForumQuestionsCommentsImageDto> images;
}
