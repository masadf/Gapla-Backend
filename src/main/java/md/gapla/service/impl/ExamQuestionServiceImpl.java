package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.courseexam.ExamQuestionDto;
import md.gapla.model.entity.courseexam.ExamQuestionAnswerEntity;
import md.gapla.model.entity.courseexam.ExamQuestionEntity;
import md.gapla.model.input.ExamQuestionInput;
import md.gapla.repository.exam.ExamQuestionAnswerRepository;
import md.gapla.repository.exam.ExamQuestionRepository;
import md.gapla.service.ExamQuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl implements ExamQuestionService {
	
	private final ExamQuestionRepository examQuestionRepository;
	private final AppMapper appMapper;
	private final ExamQuestionAnswerRepository examQuestionAnswerRepository;
	
	@Override
	public List<ExamQuestionDto> getAll() {
		List<ExamQuestionEntity> list = examQuestionRepository.findAll();
		return list.stream().map(appMapper::map).collect(Collectors.toList());
	}
	
	@Override
	public ExamQuestionDto getExamQuestion(Long examQuestionId) {
		ExamQuestionEntity entity = examQuestionRepository.findById(examQuestionId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Question with id = " + examQuestionId + " not found."));
		return appMapper.map(entity);
	}
	
	@Override
	public ExamQuestionEntity getExamQuestionEntity(Long examQuestionId) {
		return examQuestionRepository.findById(examQuestionId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Question with id = " + examQuestionId + " not found."));
	}
	
	@Override
	public ExamQuestionDto addExamQuestion(ExamQuestionInput input) {
		if (input.getExamQuestionId() != null && examQuestionRepository.existsById(input.getExamQuestionId())) {
			throw new InvalidRequestException("Exam Question already exists.");
		}
		ExamQuestionEntity entity = appMapper.map(input);
		List<ExamQuestionAnswerEntity> variants = examQuestionAnswerRepository.findAllById(input.getVariants());
		entity.setVariants(variants);
		entity = examQuestionRepository.save(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public ExamQuestionDto updateExamQuestion(ExamQuestionInput input) {
		ExamQuestionEntity entity = examQuestionRepository.findById(input.getExamQuestionId())
				.orElseThrow(() -> new EntityNotFoundException("Exam Question with id = " + input.getExamQuestionId() + " not found."));
		entity.setValue(input.getValue());
		entity.setStatus(input.getStatus());
		entity.setQuestionType(input.getQuestionType());
		
		// Update variants
		List<ExamQuestionAnswerEntity> updatedVariants = new ArrayList<>();
		for (Long variantId : input.getVariants()) {
			ExamQuestionAnswerEntity variantEntity = entity.getVariants().stream()
					.filter(variant -> variant.getExamQuestionAnswerId().equals(variantId))
					.findFirst()
					.orElseThrow(() -> new EntityNotFoundException("Variant with id = " + variantId + " not found."));
			
			updatedVariants.add(variantEntity);
		}
		entity.getVariants().clear();
		entity.getVariants().addAll(updatedVariants);

		entity = examQuestionRepository.saveAndFlush(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public void deleteExamQuestion(Long examQuestionId) {
		examQuestionRepository.deleteById(examQuestionId);
	}
}
