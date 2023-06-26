package md.gapla.model.entity.meeting;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;


@Entity
@Table(name = "meetingtypelanguage", schema = "public")
@Data
public class MeetingTypeLanguageEntity {

    @Id
    @Column(name = "meetingtypelanguageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingtypelanguageid;

    @ManyToOne
    @JoinColumn(name = "meetingtypeid", referencedColumnName = "meetingtypeid")
    private MeetingTypeEntity meetingType;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

    @Column(name = "value")
    private String value;


}
