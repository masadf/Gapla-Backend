package md.gapla.model.input.forum;

import lombok.Data;
import md.gapla.model.dto.forum.ForumQuestionsImageDto;

import java.util.List;

@Data
public class ForumQuestionsInput {

    private Long forumId;

    private String forumQuestionLabel;

    private String forumQuestionContent;

    private List<ForumQuestionsImageDto> images;

}
