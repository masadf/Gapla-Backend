package md.gapla.model.entity.forum;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.ObjectStatusEnum;

@Entity
@Table(name = "forumquestionscoomentsimage", schema = "public")
@Data
public class ForumQuestionsCommentsImageEntity {
    @Id
    @Column(name = "forumquestionscoomentsimageid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forumQuestionsCoomentsImageid;


    @Column( name = "forumquestionscommentid")
    private Long forumQuestionsCommentId;


    @Column(name = "value")
    private String value;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;
}
