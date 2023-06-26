package md.gapla.model.dto.view;

import lombok.Data;

import java.util.Date;

@Data
public class ForumsViewDto {
    private String forumId;

    private String forumName;

    private String forumTypeName;
    private Long forumTypeDetailId;
    private String forumTypeNameLanguage;

    private String forumStatus;

    private String forumTypeStatus;
    private Long forumTypeId;
    private Date createdTime;

    private String languageCode;
    private Long forumQuestionCount;

}
