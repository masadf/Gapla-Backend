package md.gapla.model.dto.account;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class AccountDto {
    private Long accountId;

    private String userName;

    private String email;
    private String passportNumber;

    private String fam;

    private String im;

    private String ot;

    private String phoneNumber;

    private Date birthdate;

    private Integer gender;

    private String status;//TODO: change to ObjectStatusEnum

    private String token;

    private String town;
    private String country;
    
    private LocalDateTime lastVisit;//Needed?
    private String avatar;//Needed?

    private Set<AccountRoleDto> roles = new HashSet<>();
}
