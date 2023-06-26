package md.gapla.model.input.forum;

import lombok.Data;

@Data
public class ForumUpdate {
    private Long forumId;
    private String forumName;
    private Long forumTypeDetailId;

}
