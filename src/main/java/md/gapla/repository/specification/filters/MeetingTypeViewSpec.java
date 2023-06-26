package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.ForumsViewEntity;
import md.gapla.model.entity.view.MeetingTypeViewEntity;

import java.io.Serial;


public class MeetingTypeViewSpec extends JpaAbstractSpec<MeetingTypeViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public MeetingTypeViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
