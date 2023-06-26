package md.gapla.model.dto.view;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingViewDto {
    private Long meetingsId;

    private String title;

    private String desctiption;

    private LocalDateTime createdDate;

    private LocalDateTime meetingDate;

    private Long meetingTypeId;

    private String courseName;

    private Long courseId;

    private Long levelLanguageId;

    private String levelLanguageName;

    private String type;

    private Long maxCount;

    private Long activeCount;

    private String value;

    private String languageCode;

    private String linkUrl;

    private String fam;

    private String accountId;

    private String recordedVideo;
    private String avatar;

}
