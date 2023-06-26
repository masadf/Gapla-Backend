package md.gapla.model.entity.lessons;

import jakarta.persistence.*;
import lombok.Data;
import md.gapla.model.entity.test.TestAnswerEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessonmaterialstype", schema = "public")
@Data
public class LessonMaterialsTypeEntity {
    @Id
    @Column(name = "lessonmaterialstypeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonMaterialsTypeId;

    @Column(name = "lessonmaterialstypename")
    private String lessonMaterialsTypeName;

    @OneToMany(mappedBy = "lessonMaterialsType")
    private List<LessonMaterialsTypeLanguageEntity> languages=new ArrayList<>();
}
