package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.CommonInfoViewEntity;
import md.gapla.model.entity.view.ForumTypeViewEntity;

import java.io.Serial;


public class ForumTypeViewSpec extends JpaAbstractSpec<ForumTypeViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public ForumTypeViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
