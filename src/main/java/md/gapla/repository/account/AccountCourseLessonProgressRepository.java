package md.gapla.repository.account;

import md.gapla.model.entity.account.AccountCourseLessonProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCourseLessonProgressRepository extends JpaRepository<AccountCourseLessonProgressEntity, Long>, JpaSpecificationExecutor<AccountCourseLessonProgressEntity> {
}
