package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.ForumQuestionViewEntity;
import md.gapla.model.entity.view.LevelLanguageViewEntity;

import java.io.Serial;


public class LevelLanguageViewSpec extends JpaAbstractSpec<LevelLanguageViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public LevelLanguageViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
