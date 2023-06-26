package md.gapla.model.entity.meeting;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "meetingtype", schema = "public")
@Data
public class MeetingTypeEntity {

    @Id
    @Column(name = "meetingtypeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingTypeId;

    @Column(name = "meetingtypecode")
    private String meetingTypeCode;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;


}
