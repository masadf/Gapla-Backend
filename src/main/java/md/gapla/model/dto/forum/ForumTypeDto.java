package md.gapla.model.dto.forum;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.Date;

@Data
public class ForumTypeDto {
    private Long forumTypeId;

    private String forumTypeName;
    private Date createdTime;
    private ObjectStatusEnum status;
}
