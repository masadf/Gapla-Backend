package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Lesson", description = "Работа с уроками")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping(value = "/list")
    public ResponseEntity<List<LessonDto>> getLessonList(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(lessonService.getLessonList(pageParamDto));
    }
    
    @Operation(summary = "Добавление урокв.")
    @PostMapping
    public ResponseEntity<LessonDto> addLessonId(@RequestBody LessonInput input) {
        return ResponseEntity.ok(lessonService.addLessonDto(input));
    }
    
    @Operation(summary = "Редактирование урока.")
    @PutMapping
    public ResponseEntity<LessonDto> updateLessonId(@RequestBody LessonInput input) {
        return ResponseEntity.ok(lessonService.updateLessonDto(input));
    }
    
    @Operation(summary = "Удаление урока.")
    @DeleteMapping(value = "/{lessonId}")
    public ResponseEntity<Void> deleteLessonById(@PathVariable("lessonId") Long lessonId) {
        lessonService.deleteLessonById(lessonId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{lessonId}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable("lessonId") Long lessonId) {
        return ResponseEntity.ok(  lessonService.getLessonById(lessonId));
    }
    
    @Operation(summary = "Получение уроков к курсу по id курса")
    @GetMapping(value = "/courseLessons/{courseId}")
    public ResponseEntity<List<LessonEntity>> getCourseLessonsById(@PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(lessonService.getLessonByCourseId(courseId));
    }
}
