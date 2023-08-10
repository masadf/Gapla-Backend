package md.gapla.model.dto.courseexam;

import lombok.Data;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.enums.ObjectStatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExamDto {
    private Long examId;

    private String examName;
    
    private LocalDateTime creationDate; //DateTime of creation

    private List<ExamTaskDto> reading = new ArrayList<>();
    private List<ExamTaskDto> listening = new ArrayList<>();
    
    private List<CourseDto> courses = new ArrayList<>();
    
    private Integer tasksQuantity;
    
    private ObjectStatusEnum status;
}
