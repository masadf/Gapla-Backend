package md.gapla.model.entity.lessons;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.enums.DocumentTypeEnum;
import md.gapla.model.enums.ObjectStatusEnum;

@Entity
@Table(name = "lessonmaterials", schema = "public")
@Data
public class LessonMaterialsEntity {
    @Id
    @Column(name = "lessonmaterialid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonMaterialId;

    @ManyToOne
    @JoinColumn(name = "lessonid", referencedColumnName = "lessonid")
    private LessonEntity lesson;

    @Column(name = "urldocument")
    private String urlDocument;

    @Column(name = "documentname")
    private String documentName;
    @Column(name = "filepathname")
    private String filePathName;

//    @Column(name = "documenttype")
//    @Enumerated(EnumType.STRING)
//    private DocumentTypeEnum documentType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ObjectStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "lessonmaterialstypeid", referencedColumnName = "lessonmaterialstypeid")
    private LessonMaterialsTypeEntity lessonMaterialsType;
}
