package md.gapla.model.dto.view;

import lombok.Data;
import md.gapla.model.dto.forum.ForumQuestionsCommentsImageDto;
import md.gapla.model.entity.forum.ForumQuestionsCommentsImageEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ForumQuestionCommentsViewDto {
    private Long forumQuestionsCommentId;

    private String forumQuestionComment;
    private String forumQuestionCommentStatus;

    private String fromReplyComent;

    private Long fromReplyComentid;

    private Long forumQuestionId;

    private String forumQuestionLabel;
    private String forumQuestionContent;

    private Long accountId;

    private String fio;

    private Long forumId;

    private String forumName;

    private String forumTypeNameLanguage;

    private String forumTypeName;

    private String forumStatus;

    private String forumTypeStatus;

    private String forumQueStionStatus;

    private Long forumTypeId;

    private String languageCode;

    private String languageName;

    private Date createdTime;

    private List<ForumQuestionsCommentsImageDto> images=new ArrayList<>();
}
