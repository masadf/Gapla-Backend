package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.dto.view.CourseViewDto;
import md.gapla.model.input.CourseInput;
import md.gapla.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "course")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/course")
@AllArgsConstructor
@CrossOrigin
public class CourseController {


    private final CourseService courseService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<CourseViewDto>> getCoursePage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(courseService.getCoursePage(pageParamDto));
    }


    @PostMapping
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseInput input) {
        return ResponseEntity.ok(courseService.addCourse(input));
    }


    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseInput input) {
        return ResponseEntity.ok(courseService.updateCourse(input));
    }

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok().build();
    }
}
