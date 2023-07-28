package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.dto.view.CourseViewDto;
import md.gapla.model.input.CourseInput;
import md.gapla.model.input.ExamInput;
import md.gapla.service.CourseService;
import md.gapla.service.ExamCourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "courseExam")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/course-exam")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Exam", description = "Работа с экзаменами")
public class CourseExamController {


    private final ExamCourseService examCourseService;
    
    @Operation(summary = "Получение всех экзаменов.")
    @GetMapping
    public ResponseEntity<List<ExamDto>> getAll() {
        return ResponseEntity.ok(examCourseService.getAll());
    }
    
    @Operation(summary = "Добавление экзамена.")
    @PostMapping
    public ResponseEntity<ExamDto> addExam(@RequestBody ExamInput input) {
        return ResponseEntity.ok(examCourseService.addExam(input));
    }
    
    @Operation(summary = "Получение экзамена по id.")
    @GetMapping(value="/{examId}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("examId") Long examId){
        return ResponseEntity.ok(examCourseService.getExam(examId));
    }
    
    @Operation(summary = "Редактирование экзамена.")
    @PutMapping
    public ResponseEntity<ExamDto> updateExam(@RequestBody ExamInput input) {
        return ResponseEntity.ok(examCourseService.updateExam(input));
    }
    
    @Operation(summary = "Получение вариантов экзамена по id курса.")
    @GetMapping(value = "/bycourse/{courseId}")
    public ResponseEntity<List<ExamDto>> getExamsByCourseId(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(examCourseService.getExamsByCourseId(courseId));
    }
    
    @Operation(summary = "Удаление экзамена по id.")
    @DeleteMapping(value = "/{examId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("examId") Long examId) {
        examCourseService.deleteExam(examId);
        return ResponseEntity.ok("Exam was successfully deleted.");
    }
}
