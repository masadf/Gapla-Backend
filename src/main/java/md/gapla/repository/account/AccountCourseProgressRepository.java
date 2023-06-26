package md.gapla.repository.account;

import md.gapla.model.entity.account.AccountCourseProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCourseProgressRepository extends JpaRepository<AccountCourseProgressEntity, Long> {

    List<AccountCourseProgressEntity> findByAccountId(Long accountId);
}
