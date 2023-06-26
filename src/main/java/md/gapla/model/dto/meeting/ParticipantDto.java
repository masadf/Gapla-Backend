package md.gapla.model.dto.meeting;

import lombok.Data;

@Data
public class ParticipantDto {
    private Long accountId;
    private String email;
    private String fam;

    private String im;

    private String ot;
    private String phonenumber;

}
