package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.dto.view.CourseViewDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.course.CourseLanguageEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.test.TestEntity;
import md.gapla.model.entity.view.CourseViewEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.CourseInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.course.CourseDetailsRepository;
import md.gapla.repository.course.CourseLanguageRepository;
import md.gapla.repository.course.CourseRepository;
import md.gapla.repository.levellanguage.LevelLanguageRepository;
import md.gapla.repository.specification.filters.CourseViewSpec;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.test.TestRepository;
import md.gapla.repository.view.CourseViewRepository;
import md.gapla.service.CourseService;
import md.gapla.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static md.gapla.util.ErrorMessagesUtils.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final AppMapper appMapper;

    private final CourseRepository courseRepository;
    private final CourseDetailsRepository courseDetailsRepository;

    private final LanguageRepository languageRepository;

    private final LevelLanguageRepository levelLanguageRepository;
    private final TestRepository testRepository;
    private final CourseViewRepository courseViewRepository;

    private final LessonService lessonService;
    private final CourseLanguageRepository courseLanguageRepository;


    @Override
    public Page<CourseViewDto> getCoursePage(PageParamDto pageParamDto) {


        Specification<CourseViewEntity> masterSpec = null;
        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new CourseViewSpec(filterCriteria));
        }
        Page<CourseViewEntity> pages = courseViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());
        return pages.map(appMapper::map);
    }

    @Override
    public CourseDto getCourse(Long courseId) {
        CourseEntity entity = getCourseEntity(courseId);
        CourseDto dto = appMapper.map(entity);
        dto.setLessons(lessonService.getLessonByCourseId(courseId).stream().map(appMapper::map).toList());
        dto.setDetails(courseDetailsRepository.findByCourseId(dto.getCourseId()).stream().map(appMapper::map).toList());
        return dto;
    }

    @Override
    public CourseEntity getCourseEntity(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public CourseDto addCourse(CourseInput input) {
        CourseEntity courseEntity = courseRepository.findByCourseNameIgnoreCase(input.getCourseName());
        if (courseEntity != null) {
            throw new InvalidRequestException("");
        }

        CourseEntity course = saveCourse(new CourseEntity(), input);
        CourseDto courseDto = appMapper.map(course);
//        List<Long> testsId = course.getTests().stream().map(TestEntity::getTestId).toList();
//        courseDto.setTests(testsId);
        return courseDto;
    }

    @Override
    public CourseDto updateCourse(CourseInput input) {
        CourseEntity courseEntity = courseRepository.findById(input.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException(""));
        CourseEntity course = saveCourse(courseEntity, input);
        CourseDto courseDto = appMapper.map(course);
//        List<Long> testsId = course.getTests().stream().map(TestEntity::getTestId).toList();
//        courseDto.setTests(testsId);
        return courseDto;
    }

    @Override
    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<CourseLanguageEntity> findAll(String languageCode) {

        List<CourseEntity> courseEntities = courseRepository.findAll();
        List<Long> courseIdS = courseEntities.stream().map(CourseEntity::getCourseId).toList();
        List<CourseLanguageEntity> courseLanguageEntities = courseLanguageRepository.findByLanguageLanguageCodeAndCourseIdIn(languageCode, courseIdS);
        return courseLanguageEntities;
    }

    @Override
    public void deleteCourseById(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(""));
        courseEntity.setStatus(ObjectStatusEnum.DISABLE);
        courseRepository.save(courseEntity);
    }


    private LanguageEntity findLanguageByCode(String languageCode) {
        return languageRepository.findByLanguageCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, languageCode)));

    }

    private LevelLanguageEntity findLevelLanguageById(Long id) {
        return levelLanguageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
    }

    private CourseEntity saveCourse(CourseEntity course, CourseInput input) {
        LanguageEntity language = findLanguageByCode(input.getLanguageCode());
        LevelLanguageEntity levelLanguage = findLevelLanguageById(input.getLevelLanguageId());

        List<TestEntity> testEntities = new ArrayList<>();
        input.getTests().forEach(r ->
        {
            TestEntity testEntity = testRepository.findById(r).orElseThrow(() -> new EntityNotFoundException(""));
            testEntities.add(testEntity);
        });
        if (input.getStatus() == null) {
            course.setStatus(ObjectStatusEnum.ENABLE);
        } else
            course.setStatus(input.getStatus());
        course.setLanguage(language);
        course.setLevelLanguage(levelLanguage);
//        course.setTests(testEntities);
        course.setCourseName(input.getCourseName());
        course = courseRepository.save(course);
        return course;
    }
}
