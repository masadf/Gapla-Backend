package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.courseexam.ExamTextDto;
import md.gapla.model.entity.courseexam.ExamTextEntity;
import md.gapla.repository.exam.ExamTextRepository;
import md.gapla.service.ExamTextService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamTextServiceImpl implements ExamTextService {
	
	private final ExamTextRepository examTextRepository;
	
	private final AppMapper appMapper;
	
	@Override
	public List<ExamTextDto> getAll() {
		List<ExamTextEntity> list = examTextRepository.findAll();
		return list.stream().map(appMapper::map).collect(Collectors.toList());
	}
	
	@Override
	public ExamTextDto getExamText(Long examTextId) {
		ExamTextEntity entity = examTextRepository.findById(examTextId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Text with id = " + examTextId + " not found."));
		return appMapper.map(entity);
	}
	
	@Override
	public ExamTextEntity getExamTextEntity(Long examTextId) {
		return examTextRepository.findById(examTextId)
				.orElseThrow(() -> new EntityNotFoundException("Exam Text with id = " + examTextId + " not found."));
	}
	
	@Override
	public ExamTextDto addExamText(ExamTextDto input) {
		if (input.getExamTextId() != null && examTextRepository.existsById(input.getExamTextId())) {
			throw new InvalidRequestException("Exam Text already exists.");
		}
		ExamTextEntity entity = appMapper.map(input);
		entity = examTextRepository.save(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public ExamTextDto updateExamText(ExamTextDto input) {
		ExamTextEntity entity = examTextRepository.findById(input.getExamTextId())
				.orElseThrow(() -> new EntityNotFoundException("Exam Text with id = " + input.getExamTextId() + " not found."));
		entity.setValue(input.getValue());
		entity = examTextRepository.save(entity);
		return appMapper.map(entity);
	}
	
	@Override
	public void deleteExamText(Long examTextId) {
		examTextRepository.deleteById(examTextId);
	}
}
