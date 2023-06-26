package md.gapla.model.input.meeting;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeetingInput {

    private Long meetingsId;
    private Long maxCount;
    private String title;
    private String desctiption;
    private String linkUrl;
    private String recordedVideo;
    private Long meetingTypeId;
    private String meetingDate;
    private String  languageCode;
    private List<Long> participants =new ArrayList<>();
}
