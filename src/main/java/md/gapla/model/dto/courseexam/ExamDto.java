package md.gapla.model.dto.courseexam;

import lombok.Data;
import md.gapla.model.entity.courseexam.ExamTaskEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamDto {
    private Long examId;

    private String examName;

    private List<ExamTaskDto> reading = new ArrayList<>();
    private List<ExamTaskDto> listening = new ArrayList<>();
}
