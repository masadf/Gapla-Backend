package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.CommonInfoViewEntity;

import java.io.Serial;


public class CommonInfoViewSpec extends JpaAbstractSpec<CommonInfoViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public CommonInfoViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
