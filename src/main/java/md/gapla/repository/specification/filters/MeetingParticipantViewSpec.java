package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.MeetingParticipantViewEntity;
import md.gapla.model.entity.view.MeetingViewEntity;

import java.io.Serial;


public class MeetingParticipantViewSpec extends JpaAbstractSpec<MeetingParticipantViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public MeetingParticipantViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
