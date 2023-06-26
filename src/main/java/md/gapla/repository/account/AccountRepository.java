package md.gapla.repository.account;

import md.gapla.model.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    Optional<AccountEntity> findByEmail(String email);
    AccountEntity findByEmailAndAccountId(String email,Long accountId);
}
