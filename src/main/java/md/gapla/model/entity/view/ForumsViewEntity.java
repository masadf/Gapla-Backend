package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "forumsview", schema = "public")
@Data
public class ForumsViewEntity {
    @Id
    @Column(name = "forumid")
    private Long forumId;

    @Column(name = "forumtypedetailid")
    private Long forumTypeDetailId;

    @Column(name = "forumname")
    private String forumName;

    @Column(name = "forumtypename")
    private String forumTypeName;

    @Column(name = "forumtypenamelanguage")
    private String forumTypeNameLanguage;

    @Column(name = "forumstatus")
    private String forumStatus;

    @Column(name = "forumtypestatus")
    private String forumTypeStatus;
    @Column(name = "forumtypeid")
    private Long forumTypeId;

    @Column(name = "forumquestioncount")
    private Long forumQuestionCount;

    @Column(name = "createdtime")
    private Date createdTime;

    @Column(name = "languagecode")
    private String languageCode;

}
