package md.gapla.repository.account;

import md.gapla.model.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    Optional<AccountEntity> findByEmail(String email);
    AccountEntity findByEmailAndAccountId(String email,Long accountId);
    
    @Query("SELECT a FROM AccountEntity a " +
            "WHERE (:name IS NULL OR a.im LIKE %:name%) " +
            "AND (:surname IS NULL OR a.fam LIKE %:surname%) " +
            "AND (:email IS NULL OR a.email LIKE %:email%) " +
            "AND (:telephoneNumber IS NULL OR a.phoneNumber LIKE %:telephoneNumber%) " +
            "AND (:role IS NULL OR :role MEMBER OF a.roles)")
    List<AccountEntity> findAccountsByFilter(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("email") String email,
            @Param("telephoneNumber") String telephoneNumber,
            @Param("role") String role
    );
}
