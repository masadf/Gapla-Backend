package md.gapla.model.entity.account;

import jakarta.persistence.*;
import lombok.*;
import md.gapla.model.enums.AccountRoleEnum;

@Entity
@Table(name = "accountrole", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRoleEntity {

    @Id
    @Column(name = "accountroleid")
//    @SequenceGenerator(name = "accountRoleGenerator", sequenceName = "marketplace.accountrole_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountRoleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private AccountRoleEnum accountRoleName;

}
