package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonMaterialsDto;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.model.input.lesson.LessonMaterialsInput;
import md.gapla.service.LessonMaterialsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController(value = "lesson-material")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/lesson-material")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Lesson Materials", description = "Работа с материалами уроков")
public class LessonMaterialsController {

    private final LessonMaterialsService lessonMaterialsService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<LessonMaterialsDto>> getLessonMaterialsPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(lessonMaterialsService.getLessonMaterialsPage(pageParamDto));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<LessonMaterialsDto>> getLessonMaterialsList(@RequestHeader("languageCode") String languageCode,
                                                                           @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(lessonMaterialsService.getLessonMaterialsList(languageCode, pageParamDto));
    }

    @PostMapping()
    public ResponseEntity<LessonMaterialsDto> addLessonMaterials(@RequestBody LessonMaterialsInput input) {
        return ResponseEntity.ok(lessonMaterialsService.addLessonMaterials(input));
    }

    @PutMapping()
    public ResponseEntity<LessonMaterialsDto> updateLessonMaterials(@RequestBody LessonMaterialsInput input) {
        return ResponseEntity.ok(lessonMaterialsService.updateLessonMaterials(input));
    }

    @GetMapping(value = "/{documentId}")
    public ResponseEntity<LessonMaterialsDto> getLessonMaterials(@PathVariable("documentId") Long documentId) {
        return ResponseEntity.ok(lessonMaterialsService.getLessonMaterials(documentId));
    }

    @DeleteMapping(value = "/{documentId}")
    public ResponseEntity<Void> deleteLessonMaterials(@PathVariable("documentId") Long documentId) {
        lessonMaterialsService.deleteLessonMaterials(documentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/upload/{documentId}")
    public ResponseEntity<LessonMaterialsDto> uploadDocument(@PathVariable("documentId") Long documentId,
                                                                 @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(lessonMaterialsService.uploadLessonMaterials(documentId,file));
    }
    @GetMapping (value = "/download/{documentId}")
    public ResponseEntity< byte[]> downloadDocument(@PathVariable("documentId") Long documentId) {
        return ResponseEntity.ok(lessonMaterialsService.downloadLessonMaterials(documentId));
    }
    
    @Operation(summary = "Получение материалов к уроку по id урока.")
    @GetMapping(value = "/lessonsMaterials/{lessonId}")
    public ResponseEntity<List<LessonMaterialsEntity>> getLessonMaterialsListByLessonId(@PathVariable("lessonId") Long lessonId){//7.3
        return ResponseEntity.ok(lessonMaterialsService.getLessonMaterialsByLessonId(lessonId));
    }
}
