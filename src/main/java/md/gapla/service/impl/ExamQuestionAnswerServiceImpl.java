package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.courseexam.ExamQuestionAnswerDto;
import md.gapla.model.entity.courseexam.ExamQuestionAnswerEntity;
import md.gapla.model.input.ExamQuestionAnswerInput;
import md.gapla.repository.exam.ExamQuestionAnswerRepository;
import md.gapla.service.ExamQuestionAnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamQuestionAnswerServiceImpl implements ExamQuestionAnswerService {
	
	private final ExamQuestionAnswerRepository repository;
	
	private final AppMapper appMapper;
	@Override
	public List<ExamQuestionAnswerDto> getAll() {
		List<ExamQuestionAnswerEntity> list = repository.findAll();
		return list.stream().map(appMapper::map).toList();
	}
	
	@Override
	public ExamQuestionAnswerDto getExamQuestionAnswer(Long examQuestionAnswerId) {
		ExamQuestionAnswerEntity entity = repository.findById(examQuestionAnswerId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Answer with id = " + examQuestionAnswerId + " not found." ));
		return appMapper.map(entity);
	}
	
	@Override
	public ExamQuestionAnswerEntity getExamQuestionAnswerEntity(Long examQuestionAnswerId) {
		return repository.findById(examQuestionAnswerId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Answer with id = " + examQuestionAnswerId + " not found." ));
	}
	
	@Override
	public ExamQuestionAnswerDto addExamQuestionAnswer(ExamQuestionAnswerDto input) {
		if (repository.findById(input.getExamQuestionAnswerId()).isPresent())
			throw new InvalidRequestException("Answer already exists.");
		ExamQuestionAnswerEntity entity = appMapper.map(input);
		entity = repository.save(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public ExamQuestionAnswerDto updateExamQuestionAnswer(ExamQuestionAnswerInput input) {
		ExamQuestionAnswerEntity entity = repository.findById(input.getExamQuestionAnswerId())
				.orElseThrow(() -> new EntityNotFoundException("Answer with id = " + input.getExamQuestionAnswerId() + " not found."));
		
		entity.setIsCorrect(input.getIsCorrect());
		entity.setValue(input.getValue());
		entity = repository.save(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public void deleteExamQuestionAnswer(Long examQuestionAnswerId) {
		repository.deleteById(examQuestionAnswerId);
	}
}
