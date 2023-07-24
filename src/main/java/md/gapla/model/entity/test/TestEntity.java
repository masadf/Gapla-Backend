package md.gapla.model.entity.test;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test", schema = "public")
@Data
public class TestEntity {
    @Id
    @Column(name = "testid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;


    @Column(name = "testname")
    private String testName;

    @ManyToOne
    @JoinColumn(name = "testtextid", referencedColumnName = "testtextid")
    private TestTextEntity testText;

    @ManyToOne
    @JoinColumn(name = "testtimetypeid", referencedColumnName = "testtimetypeid")
    private TestTimeTypeEntity testTimeType;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "testquestiontest", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "testid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "testquestionid")
            }
    )
    private List<TestQuestionEntity> questions = new ArrayList<>();
    
    @Column(name = "creationdate")
    private LocalDateTime creationDate; //Creation date and time;
}
