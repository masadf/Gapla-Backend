package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "forumtype", schema = "public")
@Data
public class ForumTypeEntity {
    @Id
    @Column(name = "forumtypeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumTypeId;


    @Column(name = "forumtypename")
    private String forumTypeName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @Column(name = "createdtime")
    @CreationTimestamp
    private Date createdTime;
}
