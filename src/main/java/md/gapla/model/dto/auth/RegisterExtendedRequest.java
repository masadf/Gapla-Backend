package md.gapla.model.dto.auth;

import lombok.Data;
import md.kobalt.security.auth.RegisterRequest;

@Data
public class RegisterExtendedRequest extends RegisterRequest {

private String birthdate;
private Long countryId;
private String town;
private Integer gender;
}
