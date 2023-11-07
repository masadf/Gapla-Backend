package md.gapla.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonDto;
import md.gapla.model.dto.lessons.LessonMaterialsDto;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.model.entity.test.TestQuestionEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.lesson.LessonInput;
import md.gapla.repository.course.CourseRepository;
import md.gapla.repository.lesson.LessonMaterialsRepository;
import md.gapla.repository.lesson.LessonRepository;
import md.gapla.repository.test.TestQuestionRepository;
import md.gapla.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static md.gapla.repository.specification.LessonSpec.courseIdEqual;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final AppMapper appMapper;

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final TestQuestionRepository testQuestionRepository;
    
    private final LessonMaterialsRepository lessonMaterialsRepository;


    @Override
    public Page<LessonDto> getLessonDtoPage(PageParamDto pageParamDto) {
        return null;
    }

    @Override
    public List<LessonDto> getLessonList(PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();
        String courseId = params.get("courseId");
        Specification<LessonEntity> specification = Specification
                .where(StringUtils.hasText(courseId) ? courseIdEqual(Long.valueOf(courseId)) : null);

        List<LessonEntity> list = lessonRepository.findAll(specification);


        return list.stream().map(appMapper::map).toList();
    }

    @Override
    public LessonDto addLessonDto(LessonInput input) {
        if(lessonRepository.findById(input.getLessonId()).isPresent())
            throw new EntityExistsException("Lesson already exists.");
        LessonEntity lesson = new LessonEntity();
        lesson.setStatus(ObjectStatusEnum.ENABLE);
    
        // Update the basic fields
        lesson.setLessonName(input.getLessonName());
        lesson.setLessonText(input.getLessonText());
        lesson.setVideoLink(input.getVideoLink());
        
        CourseEntity course = findByCourseId(input.getCourseId());
        lesson.setCourse(course);
    
        // Update the questions associated with the lesson
        List<Long> questionIds = input.getQuestions();
        List<TestQuestionEntity> newQuestions = questionIds.stream()
                .map(this::findByTestQuestionId)
                .collect(Collectors.toList());
        lesson.setQuestions(newQuestions);
    
        // Update the materials associated with the lesson
        List<Long> materialIds = input.getMaterials();
        List<LessonMaterialsEntity> newMaterials = materialIds.stream()
                .map(this::findByLessonMaterialId)
                .collect(Collectors.toList());
        lesson.setMaterials(newMaterials);
        
        lesson = lessonRepository.save(lesson);
        return appMapper.map(lesson);
    }
    
    @Override
    public LessonDto updateLessonDto(LessonInput input) {
        LessonEntity lesson = lessonRepository.findById(input.getLessonId())
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
        
        // Update the basic fields
        lesson.setLessonName(input.getLessonName());
        lesson.setLessonText(input.getLessonText());
        lesson.setVideoLink(input.getVideoLink());
        
        CourseEntity course = findByCourseId(input.getCourseId());
        lesson.setCourse(course);
        
        // Update the questions associated with the lesson
        List<Long> questionIds = input.getQuestions();
        List<TestQuestionEntity> newQuestions = questionIds.stream()
                .map(this::findByTestQuestionId)
                .collect(Collectors.toList());
        lesson.setQuestions(newQuestions);
        
        // Update the materials associated with the lesson
        List<Long> materialIds = input.getMaterials();
        List<LessonMaterialsEntity> newMaterials = materialIds.stream()
                .map(this::findByLessonMaterialId)
                .collect(Collectors.toList());
        lesson.setMaterials(newMaterials);
        
        // Save the updated lesson
        lesson = lessonRepository.save(lesson);
        
        return appMapper.map(lesson);
    }


    @Override
    public void deleteLessonById(Long lessonId) {
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new EntityNotFoundException(""));
        lesson.setStatus(ObjectStatusEnum.DISABLE);
        lessonRepository.save(lesson);
    }

    @Override
    public LessonDto getLessonById(Long lessonId) {
        LessonEntity  lesson=lessonRepository.findById(lessonId).orElseThrow(()->new EntityNotFoundException("Not Found"));
        LessonDto dto=appMapper.map(lesson);
        return dto;
    }

    @Override
    public List<LessonDto> getLessonByCourseId(Long courseId) {
        return lessonRepository.findByCourseCourseId(courseId).stream().map(appMapper::map).toList();
    }


    private CourseEntity findByCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(""));
    }

    private TestQuestionEntity findByTestQuestionId(Long testQuestionId) {
        return testQuestionRepository.findById(testQuestionId).orElseThrow(() -> new EntityNotFoundException(""));
    }
    
    private LessonMaterialsEntity findByLessonMaterialId(Long lessonMaterialId) {
        return lessonMaterialsRepository.findById(lessonMaterialId).orElseThrow(() -> new EntityNotFoundException(""));
    }

}
