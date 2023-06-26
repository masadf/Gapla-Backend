package md.gapla.repository.account;

import md.gapla.model.entity.account.AccountRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {
}
