package md.gapla.model.input.test;

import lombok.Data;
import md.gapla.model.dto.test.TestTimeTypeDetailDto;

import java.util.List;

@Data
public class TestTimeTypeLanguageInput {
    private Long testTimeTypeLanguageId;
    private String languageCode;

    private String value;
    private List<TestTimeTypeDetailDto> details;
}
