package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.ExamInput;
import md.gapla.repository.course.CourseRepository;
import md.gapla.repository.exam.*;
import md.gapla.service.ExamCourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamCourseServiceImpl implements ExamCourseService {
	
	private final ExamRepository examRepository;
	private final ExamTaskRepository examTaskRepository;
	
	private final TestServiceImpl testServiceImpl;
	
	private final AppMapper appMapper;
	
	private final CourseRepository courseRepository;
	
	
	@Override
	public List<ExamDto> getExamsByCourseId(Long courseId) {
		
		List<ExamEntity> examEntities = examRepository.findByCoursesCourseId(courseId);
		
		return examEntities.stream().map(testServiceImpl::fillExamToDto).collect(Collectors.toList());
	}
	
	@Override
	public ExamDto getExam(Long examId) {
		ExamEntity entity = getExamEntity(examId);
		
		return appMapper.map(entity);
	}
	
	@Override
	public ExamEntity getExamEntity(Long examId) {
		return examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Not found"));
	}
	
	@Override
	public ExamDto addExam(ExamInput input) {
        Optional<ExamEntity> examEntity = examRepository.findById(input.getExamId());
        if(examEntity.isPresent())
            throw new InvalidRequestException("Exam with name = " + input.getExamName() + " already exists.");
		
        ExamEntity newExamEntity = new ExamEntity();
		
		newExamEntity.setExamName(input.getExamName());
		
		newExamEntity.setCreationDate(LocalDateTime.now());
		
		List<ExamTaskEntity> examTasksToAdd = examTaskRepository.findAllById(input.getExamTasksToAdd());
		newExamEntity.getTasks().addAll(examTasksToAdd);
		
		List<CourseEntity> coursesToAdd = courseRepository.findAllById(input.getCoursesToAdd());
		newExamEntity.getCourses().addAll(coursesToAdd);
		
		newExamEntity.setStatus(ObjectStatusEnum.ENABLE);
		
		newExamEntity = examRepository.save(newExamEntity);
		
		return appMapper.map(newExamEntity);
	}
	
	@Override
	public ExamDto updateExam(ExamInput input) {
		ExamEntity examEntity = examRepository.findById(input.getExamId()).orElseThrow(() -> new EntityNotFoundException("Exam with id = " + input.getExamId() + " not found."));
		
		List<ExamTaskEntity> examTasksToAdd = examTaskRepository.findAllById(input.getExamTasksToAdd());
		examEntity.getTasks().addAll(examTasksToAdd);
		
		List<CourseEntity> coursesToAdd = courseRepository.findAllById(input.getCoursesToAdd());
		examEntity.getCourses().addAll(coursesToAdd);
		
		List<ExamTaskEntity> examTasksToDelete = examTaskRepository.findAllById(input.getExamTasksToDelete());
		examEntity.getTasks().removeAll(examTasksToDelete);
		
		List<CourseEntity> coursesToDelete = courseRepository.findAllById(input.getCoursesToDelete());
		examEntity.getCourses().removeAll(coursesToDelete);
		
		examEntity.setExamName(input.getExamName());
		
		examEntity = examRepository.save(examEntity);
		
		return appMapper.map(examEntity);
	}
	
	@Override
	public List<ExamDto> getAll() {
		return examRepository.findAll().stream().map(appMapper::map).toList();
	}
	
	@Override
	public void deleteExam(Long examId) {
		ExamEntity examEntity = examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Exam with id = " + examId + " not found"));
		examEntity.setStatus(ObjectStatusEnum.DISABLE);
		examRepository.save(examEntity);
	}
	
	//?
	private Integer getRandoNumberTest(Integer max) {
		return ThreadLocalRandom.current().nextInt(0, max);
	}
}
