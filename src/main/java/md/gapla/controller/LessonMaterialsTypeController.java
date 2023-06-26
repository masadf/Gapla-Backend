package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeDto;
import md.gapla.model.dto.lessons.LessonMaterialsTypeLanguageDto;
import md.gapla.model.input.lesson.LessonMaterialsTypeLanguageInput;
import md.gapla.service.LessonMaterialsTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "lesson-material-type")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/lesson-material-type")
@AllArgsConstructor
@CrossOrigin
public class LessonMaterialsTypeController {
    private final LessonMaterialsTypeService lessonMaterialsTypeService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<LessonMaterialsTypeDto>> getLessonMaterialsTypePage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(lessonMaterialsTypeService.getLessonMaterialsTypeDtoPage(pageParamDto));
    }

    @PostMapping(value = "/language/page/{lessonMaterialsTypeId}")
    public ResponseEntity<Page<LessonMaterialsTypeLanguageDto>> getLessonMaterialsTypeLanguageDtoPage(@PathVariable("lessonMaterialsTypeId") Long lessonMaterialsTypeId,
                                                                                                      @RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(lessonMaterialsTypeService.getLessonMaterialsTypeLanguagePage(lessonMaterialsTypeId,pageParamDto));
    }

    @PostMapping
    public ResponseEntity<LessonMaterialsTypeLanguageDto> addLessonMaterialsTypeLanguage(@RequestBody LessonMaterialsTypeLanguageInput input) {
        return ResponseEntity.ok(lessonMaterialsTypeService.addLessonMaterialsTypeLanguage(input));
    }

    @PutMapping
    public ResponseEntity<LessonMaterialsTypeLanguageDto> updateLessonMaterialsTypeLanguage(@RequestBody LessonMaterialsTypeLanguageInput input) {
        return ResponseEntity.ok(lessonMaterialsTypeService.updateLessonMaterialsTypeLanguage(input));
    }
}
