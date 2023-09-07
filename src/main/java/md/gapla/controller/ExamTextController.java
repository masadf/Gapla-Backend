package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.courseexam.ExamTextDto;
import md.gapla.model.entity.courseexam.ExamTextEntity;
import md.gapla.service.ExamTextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam-texts")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Exam Text", description = "Работа с текстками к заданиям")
public class ExamTextController {
	
	private final ExamTextService examTextService;
	
	@Operation(summary = "Получение всех текстов.")
	@GetMapping
	public ResponseEntity<List<ExamTextDto>> getAllExamTexts() {
		List<ExamTextDto> examQuestions = examTextService.getAll();
		return ResponseEntity.ok(examQuestions);
	}
	
	@Operation(summary = "Получение текста по id.")
	@GetMapping("/{examTextId}")
	public ResponseEntity<ExamTextDto> getExamText(@PathVariable Long examTextId) {
		ExamTextDto examQuestion = examTextService.getExamText(examTextId);
		return ResponseEntity.ok(examQuestion);
	}
	
	@Operation(summary = "Получение энтити текста по id.")
	@GetMapping("/entity/{examTextId}")
	public ResponseEntity<ExamTextEntity> getExamTextEntity(@PathVariable Long examTextId) {
		ExamTextEntity examQuestion = examTextService.getExamTextEntity(examTextId);
		return ResponseEntity.ok(examQuestion);
		
	}
	
	@Operation(summary = "Добавление текста.")
	@PostMapping
	public ResponseEntity<ExamTextDto> addExamText(@RequestBody ExamTextDto examQuestionDto) {
		ExamTextDto addedExamQuestion = examTextService.addExamText(examQuestionDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedExamQuestion);
	}
	
	@Operation(summary = "Редактирование текста по id.")
	@PutMapping
	public ResponseEntity<ExamTextDto> updateExamText(@RequestBody ExamTextDto examQuestionDto) {
		ExamTextDto updatedExamQuestion = examTextService.updateExamText(examQuestionDto);
		return ResponseEntity.ok(updatedExamQuestion);
	}
	
	@Operation(summary = "Удаление текста по id.")
	@DeleteMapping("/{examTextId}")
	public ResponseEntity<String> deleteExamText(@PathVariable Long examTextId) {
		examTextService.deleteExamText(examTextId);
		return ResponseEntity.ok("Text has been deleted.");
	}
}
