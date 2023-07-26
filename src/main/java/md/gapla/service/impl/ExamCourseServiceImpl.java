package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.input.ExamInput;
import md.gapla.repository.exam.*;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.service.ExamCourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
            throw new InvalidRequestException("");
        
//        ExamEntity newExamEntity =
		return null;
	}
	
	@Override
    //TODO
	public ExamDto updateExam(ExamInput input) {
		return null;
	}
	
	@Override
	public List<ExamEntity> findAll() {
		return examRepository.findAll();
	}
	
	@Override
    //TODO : disable
	public void deleteExam(Long examId) {
		//        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(""));
		//        courseEntity.setStatus(ObjectStatusEnum.DISABLE);
		//        courseRepository.save(courseEntity);
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
