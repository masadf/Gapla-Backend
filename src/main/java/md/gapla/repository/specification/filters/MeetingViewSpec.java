package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.MeetingTypeViewEntity;
import md.gapla.model.entity.view.MeetingViewEntity;

import java.io.Serial;


public class MeetingViewSpec extends JpaAbstractSpec<MeetingViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public MeetingViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
