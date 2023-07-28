package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
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
public class CourseExamController {


    private final ExamCourseService examCourseService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<ExamDto>> getCoursePage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(examCourseService.getExamPage(pageParamDto));
    }
//
    @PostMapping(value = "/add-exam")
    public ResponseEntity<ExamDto> addExam(@RequestBody ExamInput input) {
        return ResponseEntity.ok(examCourseService.addExam(input));
    }
//
//
//    @PutMapping
//    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseInput input) {
//        return ResponseEntity.ok(courseService.updateCourse(input));
//    }
    @Operation(summary = "Получение вариантов экзамена по id курса.")
    @GetMapping(value = "/bycourse/{courseId}")
    public ResponseEntity<List<ExamDto>> getCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(examCourseService.getExamsByCourseId(courseId));
    }

//    @DeleteMapping(value = "/{courseId}")
//    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable("courseId") Long courseId) {
//        courseService.deleteCourseById(courseId);
//        return ResponseEntity.ok().build();
//    }
}
