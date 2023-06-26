package md.gapla.model.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "forumtypeview", schema = "public")
@Data
public class ForumTypeViewEntity {
    @Id
    @Column(name = "forumtypedetailid")
    private String forumTypeDetailId;

    @Column(name = "value")
    private String value;

    @Column(name = "forumtypename")
    private String forumTypeName;

    @Column(name = "status")
    private String status;

    @Column(name = "forumtypeid")
    private Long forumTypeId;
    @Column(name = "languageid")
    private Long languageId;

    @Column(name = "languagecode")
    private String languageCode;

}
