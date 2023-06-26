package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "forums", schema = "public")
@Data
public class ForumEntity {
    @Id
    @Column(name = "forumid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumId;

    @Column(name = "forumname")
    private String forumName;


    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    private AccountEntity account;


    @ManyToOne
    @JoinColumn(name = "forumtypedetailid", referencedColumnName = "forumtypedetailid")
    private ForumTypeDetailEntity forumTypeDetail;

    @CreationTimestamp
    @Column(name = "createdtime")
    private Date createdTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;
}
