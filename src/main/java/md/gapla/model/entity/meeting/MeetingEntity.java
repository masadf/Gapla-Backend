package md.gapla.model.entity.meeting;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "meetings", schema = "public")
@Data
public class MeetingEntity {

    @Id
    @Column(name = "meetingsid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingsId;

    @Column(name = "meetingtypeid")
    private Long meetingTypeId;
    @Column(name = "title")
    private String title;

    @Column(name = "desctiption")
    private String description;

    @Column(name = "linkurl")
    private String linkUrl;

    @CreationTimestamp
    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "meetingdate")
    private LocalDateTime meetingDate;

    @Column(name = "maxcount")
    private Long maxCount;

    @Column(name = "recordedvideo")
    private String recordedVideo;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "createdaccountid", referencedColumnName = "accountid")
    private AccountEntity createdAccount;
    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "meetingparticipant", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "meetingsid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "accountid")
            }
    )
    private List<AccountEntity> participants = new ArrayList<>();

}
