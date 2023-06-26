package md.gapla.model.dto.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import md.gapla.model.dto.forum.ForumQuestionsImageDto;

import java.util.Date;
import java.util.List;

@Data
public class ForumQuestionViewDto {
    private Long forumQuestionId;

    private String forumQuestionLabel;

    private String forumQuestionContent;

    private Long accountId;

    private String fio;

    private Long forumId;
    private String forumName;

    private String forumTypenameLanguage;

    private String forumTypeName;

    private String forumStatus;

    private String forumTypeStatus;

    private Long forumTypeId;

    private String languageCode;

    private String languageName;

    private String forumQuestionStatus;

    private Date createdTime;
    private Long forumQuestionsCommentsCount;

    private  List<ForumQuestionsImageDto> images;
}
