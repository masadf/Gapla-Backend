package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "forumquestionsview", schema = "public")
@Data
public class ForumQuestionViewEntity {
    @Id
    @Column(name = "forumquestionid")
    private Long forumQuestionId;

    @Column(name = "forumquestionlabel")
    private String forumQuestionLabel;


    @Column(name = "forumquestioncontent")
    private String forumQuestionContent;

    @Column(name = "accountid")
    private Long accountId;

    @Column(name = "fio")
    private String fio;

    @Column(name = "forumid")
    private Long forumId;
    @Column(name = "forumname")
    private String forumName;

    @Column(name = "forumtypenamelanguage")
    private String forumTypenameLanguage;

    @Column(name = "forumtypename")
    private String forumTypeName;

    @Column(name = "forumstatus")
    private String forumStatus;

    @Column(name = "forumtypestatus")
    private String forumTypeStatus;

    @Column(name = "forumtypeid")
    private Long forumTypeId;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "languagename")
    private String languageName;

    @Column(name = "forumquestionstatus")
    private String forumQuestionStatus;

    @Column(name = "createdtime")
    private Date createdTime;

    @Column(name = "forumquestionscommentscount")
    private Long forumQuestionsCommentsCount;
}
