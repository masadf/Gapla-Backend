package md.gapla.model.dto.test;

import lombok.Data;
import md.gapla.model.dto.LanguageDto;
import md.gapla.model.entity.test.TestTimeTypeDetailEntity;

import java.util.List;

@Data
public class TestTimeTypeLanguageDto {
    private Long testTimeTypeLanguageId;

    private String value;

    private Long testTime;
    private List<TestTimeTypeDetailDto> details;
}
