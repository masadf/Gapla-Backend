package md.gapla.model.dto.account;

import lombok.Data;
import md.gapla.model.enums.AccountRoleEnum;

@Data
public class AccountRoleDto {

    private Long accountRoleId;

    private AccountRoleEnum accountRoleName;

    private String value;

}
