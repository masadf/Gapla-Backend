package md.gapla.model.entity.ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestQuestionTypeTestTimeTypeIds  implements Serializable {

    private Long testTimeTypeId;

    private Long testQuestionTypeId;
}
