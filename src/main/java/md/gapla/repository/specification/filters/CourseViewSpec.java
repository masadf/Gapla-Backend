package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.CourseViewEntity;
import md.gapla.model.entity.view.LevelLanguageViewEntity;

import java.io.Serial;


public class CourseViewSpec extends JpaAbstractSpec<CourseViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public CourseViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
