package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.courseexam.ExamQuestionDto;
import md.gapla.model.entity.courseexam.ExamQuestionEntity;
import md.gapla.model.input.ExamQuestionInput;
import md.gapla.service.ExamQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam-questions")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Exam Question", description = "Работа с вопросами к заданиям экзаменов.")
public class ExamQuestionController {
	
	private final ExamQuestionService examQuestionService;
	
	@Operation(summary = "Получение всех вопросов.")
	@GetMapping
	public ResponseEntity<List<ExamQuestionDto>> getAllExamQuestions() {
		List<ExamQuestionDto> examQuestions = examQuestionService.getAll();
		return ResponseEntity.ok(examQuestions);
	}
	
	@Operation(summary = "Получение вопроса по id.")
	@GetMapping("/{examQuestionId}")
	public ResponseEntity<ExamQuestionDto> getExamQuestion(@PathVariable Long examQuestionId) {
		ExamQuestionDto examQuestion = examQuestionService.getExamQuestion(examQuestionId);
		return ResponseEntity.ok(examQuestion);
	}
	
	@Operation(summary = "Получение энтити вопроса по id.")
	@GetMapping("/entity/{examQuestionId}")
	public ResponseEntity<ExamQuestionEntity> getExamQuestionEntity(@PathVariable Long examQuestionId) {
		ExamQuestionEntity examQuestion = examQuestionService.getExamQuestionEntity(examQuestionId);
		return ResponseEntity.ok(examQuestion);
		
	}
	
	@Operation(summary = "Добавление вопроса.")
	@PostMapping
	public ResponseEntity<ExamQuestionDto> addExamQuestion(@RequestBody ExamQuestionInput examQuestionDto) {
		ExamQuestionDto addedExamQuestion = examQuestionService.addExamQuestion(examQuestionDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedExamQuestion);
	}
	
	@Operation(summary = "Обновление вопроса по id.")
	@PutMapping
	public ResponseEntity<ExamQuestionDto> updateExamQuestion(@RequestBody ExamQuestionInput examQuestionDto) {
		ExamQuestionDto updatedExamQuestion = examQuestionService.updateExamQuestion(examQuestionDto);
		return ResponseEntity.ok(updatedExamQuestion);
	}
	
	@Operation(summary = "Удаление вопроса по id.")
	@DeleteMapping("/{examQuestionId}")
	public ResponseEntity<String> deleteExamQuestion(@PathVariable Long examQuestionId) {
		examQuestionService.deleteExamQuestion(examQuestionId);
		return ResponseEntity.ok("Question has been deleted.");
	}
}

