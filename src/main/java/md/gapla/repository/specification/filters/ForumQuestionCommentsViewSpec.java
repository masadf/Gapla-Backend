package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.ForumQuestionCommentsViewEntity;
import md.gapla.model.entity.view.ForumQuestionViewEntity;

import java.io.Serial;


public class ForumQuestionCommentsViewSpec extends JpaAbstractSpec<ForumQuestionCommentsViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public ForumQuestionCommentsViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
