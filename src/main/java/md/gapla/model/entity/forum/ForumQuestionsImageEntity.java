package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "forumquestionsimage", schema = "public")
@Data
public class ForumQuestionsImageEntity {
    @Id
    @Column(name = "forumquestionsimageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumQuestionsImageId;


    @Column( name = "forumquestionsid")
    private long forumQuestionsId;


    @Column(name = "value")
    private String value;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;
}
