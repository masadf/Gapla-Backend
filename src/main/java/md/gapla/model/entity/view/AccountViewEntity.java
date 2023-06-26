package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "accountview", schema = "public")
@Data
public class AccountViewEntity {
    @Id
    @Column(name = "accountid")
    private Long accountId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "fio")
    private String fio;

    @Column(name = "passportnumber")
    private String passportNumber;

    @Column(name = "gender")
    private Integer gender;
    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "lastvisit")
    private LocalDateTime lastVisit;
    @Column(name = "country")
    private String country;

    @Column(name = "town")
    private String town;

    @Column(name = "code")
    private String role;
}
