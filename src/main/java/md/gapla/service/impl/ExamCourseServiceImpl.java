package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.ExamInput;
import md.gapla.repository.course.CourseRepository;
import md.gapla.repository.exam.*;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.service.ExamCourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamCourseServiceImpl implements ExamCourseService {
	
	private final ExamRepository examRepository;
	private final ExamTaskRepository examTaskRepository;
	
	private final ExamQuestionRepository examQuestionRepository;
	private final ExamQuestionAnswerRepository examQuestionAnswerRepository;
	
	private final ExamTextRepository examTextRepository;
	
	private final TestServiceImpl testServiceImpl;
	
	private final AppMapper appMapper;
	
	private final CourseRepository courseRepository;
	
	
	@Override
	public List<ExamDto> getExamsByCourseId(Long courseId) {
		
		List<ExamEntity> examEntities = examRepository.findByCourses(courseId);
		
		return examEntities.stream().map(testServiceImpl::fillExamToDto).collect(Collectors.toList());
	}
	
	@Override
    //TODO
	public Page<ExamDto> getExamPage(PageParamDto pageParamDto) {
		return null;
//        Specification<CourseViewEntity> masterSpec = null;
//        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
//            masterSpec = Specification.where(masterSpec).and(new CourseViewSpec(filterCriteria));
//        }
//        Page<CourseViewEntity> pages = courseViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());
//        return pages.map(appMapper::map);
	}
	
	@Override
    //TODO
	public ExamDto getExam(Long examId) {
		ExamEntity entity = getExamEntity(examId);
//        ExamDto dto = appMapper.map(entity);
		//Set fields
		return null;
	}
	
	@Override
	public ExamEntity getExamEntity(Long examId) {
		return examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Not found"));
	}
	
	@Override
    //TODO
	public ExamDto addExam(ExamInput input) {
        ExamEntity examEntity = examRepository.findByExamName(input.getExamName());
        if(examEntity != null)
            throw new InvalidRequestException("Exam with name = " + input.getExamName() + " already exists.");
		
        ExamEntity newExamEntity = new ExamEntity();
		
		newExamEntity.setExamName(input.getExamName());
		
		newExamEntity.setCreationDate(LocalDateTime.now());
		
		List<ExamTaskEntity> examTasksToAdd = examTaskRepository.findAllById(input.getExamTasksToAdd());
		newExamEntity.getTasks().addAll(examTasksToAdd);
		
		List<CourseEntity> coursesToAdd = courseRepository.findAllById(input.getCoursesToAdd());
		newExamEntity.getCourses().addAll(coursesToAdd);
		
		newExamEntity.setStatus(ObjectStatusEnum.ENABLE);
		
		examRepository.save(newExamEntity);
		return null;
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
		
		examRepository.save(examEntity);
		
		return appMapper.map(examEntity);
	}
	
	@Override
	public List<ExamEntity> findAll() {
		return examRepository.findAll();
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
    
    //TODO
    private ExamEntity saveExam(ExamEntity exam, ExamInput input){
        //Set fields
        return null;
    }
}
