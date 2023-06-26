package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.CourseViewEntity;
import md.gapla.model.entity.view.TimeTypeViewEntity;

import java.io.Serial;


public class TestTimeViewSpec extends JpaAbstractSpec<TimeTypeViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public TestTimeViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
