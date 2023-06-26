package md.gapla.model.input;

import lombok.Data;

import java.util.List;

@Data
public class CommonInfoLanguageInput {

    private String labelValue;
    private String contentValue;
    private String commonInfoTypeCode;
    private String commonInfoCode;
    private String languageCode;
    private List<CommonInfoLanguageDetailInput> details;
}
