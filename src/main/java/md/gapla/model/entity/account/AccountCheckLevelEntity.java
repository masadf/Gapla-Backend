package md.gapla.model.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import md.gapla.model.entity.courseexam.CourseExamEntity;
import md.gapla.model.entity.courseexam.ExamQuestionEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accountchecklevel", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCheckLevelEntity {

    @Id
    @Column(name = "accountchecklevelid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountCheckLevelId;

    @Column(name = "accountid")
    private Long accountId;

    @Column(name = "reading")
    private Double reading;

    @Column(name = "listening")
    private Double listening;

    @Column(name = "total")
    private Double total;

    @CreationTimestamp
    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "courseexamaccountchecklevel", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "accountchecklevelid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "courseexamid")
            }
    )
    private List<CourseExamEntity> courseExamList = new ArrayList<>();

}
