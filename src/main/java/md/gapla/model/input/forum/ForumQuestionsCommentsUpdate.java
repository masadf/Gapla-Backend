package md.gapla.model.input.forum;

import lombok.Data;
import md.gapla.model.dto.forum.ForumQuestionsCommentsImageDto;

import java.util.List;

@Data
public class ForumQuestionsCommentsUpdate {

    private Long forumQuestionsCommentId;

    private Long forumQuestionId;

    private String comment;

    private List<ForumQuestionsCommentsImageDto> images;
}
