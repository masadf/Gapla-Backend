package md.gapla.repository.specification.filters;

import md.gapla.model.entity.view.AccountViewEntity;
import md.gapla.model.entity.view.CommonInfoViewEntity;

import java.io.Serial;


public class AccountViewSpec extends JpaAbstractSpec<AccountViewEntity> {

    @Serial
    private static final long serialVersionUID = 1L;

    public AccountViewSpec(FilterCriteria criteria) {
        super(criteria);
    }
}
