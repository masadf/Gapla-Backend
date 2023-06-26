package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.ForumTypeViewEntity;
import md.gapla.model.entity.view.ForumsViewEntity;

import java.io.Serial;


public class ForumViewSpec extends JpaAbstractSpec<ForumsViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public ForumViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
