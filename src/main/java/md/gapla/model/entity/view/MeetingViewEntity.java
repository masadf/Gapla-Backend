package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "meetingview", schema = "public")
@Data
public class MeetingViewEntity {
    @Id
    @Column(name = "meetingsid")
    private Long meetingsId;

    @Column(name = "title")
    private String title;

    @Column(name = "desctiption")
    private String desctiption;

    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "meetingdate")
    private LocalDateTime meetingDate;

    @Column(name = "meetingtypeid")
    private Long meetingTypeId;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "courseid")
    private Long courseId;

    @Column(name = "levellanguageid")
    private Long levelLanguageId;

    @Column(name = "levellanguagename")
    private String levelLanguageName;

    @Column(name = "type")
    private String type;

    @Column(name = "maxcount")
    private Long maxCount;

    @Column(name = "activecount")
    private Long activeCount;

    @Column(name = "value")
    private String value;

    @Column(name = "languagecode")
    private String languageCode;

    @Column(name = "linkurl")
    private String linkUrl;

    @Column(name = "fam")
    private String fam;

    @Column(name = "accountid")
    private String accountId;

    @Column(name = "recordedvideo")
    private String recordedVideo;
    @Column(name = "avatar")
    private String avatar;

}
