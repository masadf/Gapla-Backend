package md.gapla.model.dto.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

@Data
public class ForumQuestionsCommentsImageDto {
    private Long forumQuestionsCoomentsImageid;


    private Long forumQuestionsCommentId;

    private String value;

    private ObjectStatusEnum status;
}
