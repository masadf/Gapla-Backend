package md.gapla.model.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "account", schema = "public")
@Data
@AllArgsConstructor
public class AccountEntity {
    public AccountEntity() {
    }

    public AccountEntity(Long accountId) {
        this.accountId = accountId;
    }

    @Id
    @Column(name = "accountid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;
    @Column(name = "passportnumber")
    private String passportNumber;

    @Column(name = "fam")
    private String fam;

    @Column(name = "im")
    private String im;

    @Column(name = "ot")
    private String ot;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "pass")
    private String password;

    @Column(name = "status")
    private String status;//TODO: ObjectStatusEnum

    @Column(name = "country")
    private String country;
    @Column(name = "town")
    private String town;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "lastvisit")
    private LocalDateTime lastVisit;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "linkaccountrole", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "accountid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "accountroleid")
            }
    )
    private List<AccountRoleEntity> roles = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
            name = "bookmarked_question",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "forum_question_id")
    )
    private List<ForumQuestionsEntity> bookmarkedQuestions = new ArrayList<>();
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<ForumQuestionsEntity> questions = new ArrayList<>();
}
