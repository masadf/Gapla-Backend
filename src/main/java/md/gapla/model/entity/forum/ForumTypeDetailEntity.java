package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "forumtypedetail", schema = "public")
@Data
public class ForumTypeDetailEntity {
    @Id
    @Column(name = "forumtypedetailid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumTypeDetailId;

    @Column(name = "forumtypeid")
    private Long forumTypeId;


    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "languageid", referencedColumnName = "languageid")
    private LanguageEntity language;

    @Column(name = "createdtime")
    @CreationTimestamp
    private Date createdTime;
}
