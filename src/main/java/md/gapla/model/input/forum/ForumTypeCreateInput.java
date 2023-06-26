package md.gapla.model.input.forum;

import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

import java.util.List;

@Data
public class ForumTypeCreateInput {

    private String forumTypeName;
    private ObjectStatusEnum status;
}
