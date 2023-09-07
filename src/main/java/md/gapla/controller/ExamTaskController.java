package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import md.gapla.model.dto.courseexam.ExamTaskDto;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.input.ExamTaskInput;
import md.gapla.service.ExamTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam-tasks")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Exam Task", description = "Работа с заданиями к экзаменам")
public class ExamTaskController {
	private final ExamTaskService examTaskService;
	
	@Operation(summary = "Получение всех заданий.")
	@GetMapping
	public ResponseEntity<List<ExamTaskDto>> getAllExamTask() {
		List<ExamTaskDto> examTasks = examTaskService.getAll();
		return ResponseEntity.ok(examTasks);
	}
	
	@Operation(summary = "Получение задания по id.")
	@GetMapping("/{examTaskId}")
	public ResponseEntity<ExamTaskDto> getExamTask(@PathVariable Long examTaskId) {
		ExamTaskDto examTask = examTaskService.getExamTask(examTaskId);
		return ResponseEntity.ok(examTask);
	}
	
	@Operation(summary = "Получение энтити задания по id.")
	@GetMapping("/entity/{examTaskId}")
	public ResponseEntity<ExamTaskEntity> getExamTaskEntity(@PathVariable Long examTaskId) {
		ExamTaskEntity examTask = examTaskService.getExamTaskEntity(examTaskId);
		return ResponseEntity.ok(examTask);
		
	}
	
	@Operation(summary = "Добавление задания.")
	@PostMapping
	public ResponseEntity<ExamTaskDto> addExamTask(@RequestBody ExamTaskInput examTaskInput) {
		ExamTaskDto addedExamTask = examTaskService.addExamTask(examTaskInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedExamTask);
	}
	
	@Operation(summary = "Редактирование задания по id.")
	@PutMapping
	public ResponseEntity<ExamTaskDto> updateExamTask(@RequestBody ExamTaskInput examTaskInput) {
		ExamTaskDto updatedExamTask = examTaskService.updateExamTask(examTaskInput);
		return ResponseEntity.ok(updatedExamTask);
	}
	
	@Operation(summary = "Удаление задания по id.")
	@DeleteMapping("/{examTaskId}")
	public ResponseEntity<String> deleteExamTask(@PathVariable Long examTaskId) {
		examTaskService.deleteExamTask(examTaskId);
		return ResponseEntity.ok("Task has been deleted.");
	}
}
