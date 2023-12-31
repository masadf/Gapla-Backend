package md.gapla.model.dto.courseexam;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import md.gapla.model.entity.courseexam.ExamQuestionEntity;
import md.gapla.model.entity.courseexam.ExamTextEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamQuestionTaskTextDto {
    private Long examQuestionTaskTextId;

    private ExamTextEntity examText;

    private Long examTaskId;

    private List<ExamQuestionDto> questions = new ArrayList<>();

}
