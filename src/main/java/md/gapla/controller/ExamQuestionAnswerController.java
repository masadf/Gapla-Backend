package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.courseexam.ExamQuestionAnswerDto;
import md.gapla.model.entity.courseexam.ExamQuestionAnswerEntity;
import md.gapla.model.input.ExamQuestionAnswerInput;
import md.gapla.service.ExamQuestionAnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam-question-answers")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Exam Question Answer", description = "Работа с ответами к вопросам экзаменов")
public class ExamQuestionAnswerController {
	private final ExamQuestionAnswerService examQuestionAnswerService;
	
	@Operation(summary = "Получение всех ответов.")
	@GetMapping
	public ResponseEntity<List<ExamQuestionAnswerDto>> getAllExamQuestionAnswers() {
		List<ExamQuestionAnswerDto> examQuestionAnswers = examQuestionAnswerService.getAll();
		return ResponseEntity.ok(examQuestionAnswers);
	}
	
	@Operation(summary = "Получение ответа по id.")
	@GetMapping("/{examQuestionAnswerId}")
	public ResponseEntity<ExamQuestionAnswerDto> getExamQuestionAnswerById(@PathVariable Long examQuestionAnswerId) {
		ExamQuestionAnswerDto examQuestionAnswer = examQuestionAnswerService.getExamQuestionAnswer(examQuestionAnswerId);
		return ResponseEntity.ok(examQuestionAnswer);
	}
	
	@Operation(summary = "Получение энтити ответа по id.")
	@GetMapping("/entity/{examQuestionAnswerId}")
	public ResponseEntity<ExamQuestionAnswerEntity> getExamQuestionAnswerEntityById(@PathVariable Long examQuestionAnswerId) {
		ExamQuestionAnswerEntity examQuestionAnswer = examQuestionAnswerService.getExamQuestionAnswerEntity(examQuestionAnswerId);
		return ResponseEntity.ok(examQuestionAnswer);
	}
	
	@Operation(summary = "Добавление ответа.")
	@PostMapping
	public ResponseEntity<ExamQuestionAnswerDto> addExamQuestionAnswer(@RequestBody ExamQuestionAnswerDto examQuestionAnswerDto) {
		ExamQuestionAnswerDto addedExamQuestionAnswer = examQuestionAnswerService.addExamQuestionAnswer(examQuestionAnswerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedExamQuestionAnswer);
	}
	
	@Operation(summary = "Редактирование ответа по id.")
	@PutMapping
	public ResponseEntity<ExamQuestionAnswerDto> updateExamQuestionAnswer(@RequestBody ExamQuestionAnswerInput examQuestionAnswerInput) {
		ExamQuestionAnswerDto updatedExamQuestionAnswer = examQuestionAnswerService.updateExamQuestionAnswer(examQuestionAnswerInput);
		return ResponseEntity.ok(updatedExamQuestionAnswer);
	}
	
	@Operation(summary = "Удаление ответа по id.")
	@DeleteMapping("/{examQuestionAnswerId}")
	public ResponseEntity<String> deleteExamQuestionAnswer(@PathVariable Long examQuestionAnswerId) {
		examQuestionAnswerService.deleteExamQuestionAnswer(examQuestionAnswerId);
		return ResponseEntity.ok("Answer was successfully deleted.");
	}
}

