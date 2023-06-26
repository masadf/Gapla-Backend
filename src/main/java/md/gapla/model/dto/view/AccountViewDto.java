package md.gapla.model.dto.view;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AccountViewDto {
    private Long accountId;

    private String username;

    private String email;

    private String fio;

    private String passportNumber;
    private Integer gender;

    private LocalDate birthDate;

    private String avatar;

    private LocalDateTime lastVisit;

    private String country;

    private String town;

    private String role;
}
