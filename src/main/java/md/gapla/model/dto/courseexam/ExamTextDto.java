package md.gapla.model.dto.courseexam;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class ExamTextDto {
    private Long examTextId;

    private String value;

}
