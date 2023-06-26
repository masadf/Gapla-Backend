package md.gapla.model.dto.forum;

import lombok.Data;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.enums.ObjectStatusEnum;

@Data
public class ForumQuestionsImageDto {
    private Long forumQuestionsImageId;

    private Long forumQuestionsId;

    private String value;

    private ObjectStatusEnum status;
}
