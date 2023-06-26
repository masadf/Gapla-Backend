package md.gapla.model.entity.meeting;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.account.AccountEntity;


@Entity
@Table(name = "meetingparticipant", schema = "public")
@Data
public class MeetingParticipantEntity {

    @Id
    @Column(name = "meetingparticipantid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingParticipantId;

    @Column(name = "meetingsid")
    private Long meetingsId;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    private AccountEntity account;
    }
