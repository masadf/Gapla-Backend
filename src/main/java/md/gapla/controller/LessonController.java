package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonDto;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.input.lesson.LessonInput;
import md.gapla.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "lesson")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/lesson")
@AllArgsConstructor
@CrossOrigin
public class LessonController {

    private final LessonService lessonService;

    @PostMapping(value = "/list")
    public ResponseEntity<List<LessonDto>> getLessonList(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(lessonService.getLessonList(pageParamDto));
    }

    @PostMapping
    public ResponseEntity<LessonDto> addLessonId(@RequestBody LessonInput input) {
        return ResponseEntity.ok(lessonService.addLessonDto(input));
    }

    @PutMapping
    public ResponseEntity<LessonDto> updateLessonId(@RequestBody LessonInput input) {
        return ResponseEntity.ok(lessonService.updateLessonDto(input));
    }

    @DeleteMapping(value = "/{lessonId}")
    public ResponseEntity<Void> deleteLessonById(@PathVariable("lessonId") Long lessonId) {
        lessonService.deleteLessonById(lessonId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{lessonId}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable("lessonId") Long lessonId) {
        return ResponseEntity.ok(  lessonService.getLessonById(lessonId));
    }
    
    //7.2
    @GetMapping(value = "/courseLessons/{courseId}")
    public ResponseEntity<List<LessonEntity>> getCourseLessonsById(@PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(lessonService.getLessonByCourseId(courseId));
    }
}
