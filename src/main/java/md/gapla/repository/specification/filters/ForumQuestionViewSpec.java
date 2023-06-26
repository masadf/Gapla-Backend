package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.ForumQuestionViewEntity;
import md.gapla.model.entity.view.ForumsViewEntity;

import java.io.Serial;


public class ForumQuestionViewSpec extends JpaAbstractSpec<ForumQuestionViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public ForumQuestionViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
