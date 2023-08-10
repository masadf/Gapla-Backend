package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.courseexam.ExamTaskDto;
import md.gapla.model.entity.courseexam.*;
import md.gapla.model.input.ExamTaskInput;
import md.gapla.repository.exam.ExamQuestionRepository;
import md.gapla.repository.exam.ExamRepository;
import md.gapla.repository.exam.ExamTaskRepository;
import md.gapla.repository.exam.ExamTextRepository;
import md.gapla.service.ExamTaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamTaskServiceImpl implements ExamTaskService{
	
	private final ExamTaskRepository examTaskRepository;
	private final AppMapper appMapper;
	private final ExamQuestionRepository examQuestionRepository;
	private final ExamTextRepository examTextRepository;
	private final ExamRepository examRepository;
	@Override
	public List<ExamTaskDto> getAll() {
		List<ExamTaskEntity> list = examTaskRepository.findAll();
		return list.stream().map(appMapper::map).collect(Collectors.toList());
	}
	
	@Override
	public ExamTaskDto getExamTask(Long examTaskId) {
		ExamTaskEntity entity = examTaskRepository.findById(examTaskId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Task with id = " + examTaskId + " not found."));
		return appMapper.map(entity);
	}
	
	@Override
	public ExamTaskEntity getExamTaskEntity(Long examTaskId) {
		return examTaskRepository.findById(examTaskId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Task with id = " + examTaskId + " not found."));
	}
	
	@Override
	public ExamTaskDto addExamTask(ExamTaskInput input) {
		if (input.getExamTaskId() != null && examTaskRepository.existsById(input.getExamTaskId())) {
			throw new InvalidRequestException("Exam Task already exists.");
		}
		
		ExamTaskEntity entity = appMapper.map(input);
		return fillExamTask(input, entity);
	}
	
	@Override
	public ExamTaskDto updateExamTask(ExamTaskInput input) {
		ExamTaskEntity entity = examTaskRepository.findById(input.getExamTaskId())
				.orElseThrow(() -> new EntityNotFoundException("Exam Task with id = " + input.getExamTaskId() + " not found."));
		
		entity.setTestQuestionTypeId(input.getTestQuestionTypeId());
		entity.setValue(input.getValue());
		entity.setAudioValue(input.getAudioValue());
		return fillExamTask(input, entity);
	}
	
	private ExamTaskDto fillExamTask(ExamTaskInput input, ExamTaskEntity entity) {
		// Update questions
		List<ExamQuestionEntity> updatedQuestions = new ArrayList<>();
		for (Long questionId : input.getQuestions()) {
			ExamQuestionEntity questionEntity = examQuestionRepository.findById(questionId)
					.orElseThrow(() -> new EntityNotFoundException("Question with id = " + questionId + " not found."));
			questionEntity.setExamTaskId(entity.getExamTaskId());
			examQuestionRepository.save(questionEntity);
			updatedQuestions.add(questionEntity);
		}
		entity.getQuestions().clear();
		entity.getQuestions().addAll(updatedQuestions);
		
		List<ExamTextEntity> updatedTexts = new ArrayList<>();
		for (Long textId : input.getTexts()) {
			ExamTextEntity textEntity = examTextRepository.findById(textId)
					.orElseThrow(() -> new EntityNotFoundException("Question with id = " + textId + " not found."));
			textEntity.setExamTaskId(entity.getExamTaskId());
			examTextRepository.save(textEntity);
			updatedTexts.add(textEntity);
		}
		entity.getTexts().clear();
		entity.getTexts().addAll(updatedTexts);
		
		ExamEntity exam = examRepository.findById(input.getExamId())
				.orElseThrow(() -> new EntityNotFoundException("Exam with id = " + input.getExamId() + " not found."));
		entity.setExamId(exam.getExamId());
		entity = examTaskRepository.save(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public void deleteExamTask(Long examTaskId) {
		examTaskRepository.deleteById(examTaskId);
	}
}
