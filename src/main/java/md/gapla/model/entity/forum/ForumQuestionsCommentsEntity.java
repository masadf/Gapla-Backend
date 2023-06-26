package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "forumquestionscomments", schema = "public")
@Data
public class ForumQuestionsCommentsEntity {
    @Id
    @Column(name = "forumquestionscommentid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumQuestionsCommentId;

    @Column(name = "replyfromcommentid")
    private Long replyFromCommentId;

    @ManyToOne
    @JoinColumn(name = "forumquestionid", referencedColumnName = "forumquestionid")
    private ForumQuestionsEntity forumQuestion;

    @Column(name = "forumquestioncomment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    private AccountEntity account;

    @CreationTimestamp
    @Column(name = "createdtime")
    private Date createdTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;
}
