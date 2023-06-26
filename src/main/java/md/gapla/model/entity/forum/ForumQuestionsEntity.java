package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "forumquestions", schema = "public")
@Data
public class ForumQuestionsEntity {
    @Id
    @Column(name = "forumquestionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumQuestionId;

    @ManyToOne
    @JoinColumn(name = "forumid", referencedColumnName = "forumid")
    private ForumEntity forum;


    @Column(name = "forumquestionlabel")
    private String forumQuestionLabel;

    @Column(name = "forumquestioncontent")
    private String forumQuestionContent;


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
