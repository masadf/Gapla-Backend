package md.gapla.repository.account;

import md.gapla.model.entity.account.AccountCheckLevelEntity;
import md.gapla.model.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountCheckLevelRepository extends JpaRepository<AccountCheckLevelEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
	@Query("SELECT acl.accountCheckLevelId FROM AccountCheckLevelEntity acl WHERE acl.accountId = :accountId")
	List<Long> findAccountCheckLevelIdsByAccountId(Long accountId);
}
