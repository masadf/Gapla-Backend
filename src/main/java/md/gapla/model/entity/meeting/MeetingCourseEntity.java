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
@Table(name = "coursemeeting", schema = "public")
@Data
public class MeetingCourseEntity {

    @Id
    @Column(name = "coursemeetingid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseMeetingId;

    @Column(name = "courseid")
    private Long courseId;

    @Column(name = "meetingid")
    private Long meetingId;



}
