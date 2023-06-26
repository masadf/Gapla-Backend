package md.gapla.model.input;

import lombok.Data;

@Data
public class AccountUpdateInput {

    private Long accountId;
    private String fam;
    private String im;
    private String ot;
    private String email;

    private String userName;
    private String password;
    private String birthdate;
    private String country;
    private String town;
    private Integer gender;

    private String phoneNumber;
}
