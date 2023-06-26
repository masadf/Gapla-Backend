package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonDto;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.entity.test.TestQuestionEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.lesson.LessonInput;
import md.gapla.repository.course.CourseRepository;
import md.gapla.repository.lesson.LessonRepository;
import md.gapla.repository.test.TestQuestionRepository;
import md.gapla.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static md.gapla.repository.specification.LessonSpec.courseIdEqual;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final AppMapper appMapper;

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final TestQuestionRepository testQuestionRepository;


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
        LessonEntity lesson = new LessonEntity();
        CourseEntity course = findByCourseId(input.getCourseId());

        lesson.setStatus(ObjectStatusEnum.ENABLE);
        lesson.setCourse(course);

        lesson = lessonRepository.save(lesson);
        if (!input.getQuestions().isEmpty()) {
            List<TestQuestionEntity> questions = new ArrayList<>();
            input.getQuestions().forEach(r -> {

                TestQuestionEntity testQuestionEntity = findByTestQuestionId(r);
                questions.add(testQuestionEntity);
            });

            lesson.getQuestions().addAll(questions);
            lesson = lessonRepository.save(lesson);
        }


        return appMapper.map(lesson);
    }

    @Override
    public LessonDto updateLessonDto(LessonInput input) {
        LessonEntity lesson = lessonRepository.findById(input.getLessonId()).orElseThrow(() -> new EntityNotFoundException(""));

        List<Long> questions = input.getQuestions();
        List<TestQuestionEntity> questionsExisted = lesson.getQuestions();
        List<TestQuestionEntity> newQuestions = new ArrayList<>();
        List<TestQuestionEntity> forDeleting = new ArrayList<>();

        CourseEntity course = findByCourseId(input.getCourseId());
        lesson.setCourse(course);

        questions.forEach(r -> {
            TestQuestionEntity testQuestionEntity = findByTestQuestionId(r);
            newQuestions.add(testQuestionEntity);
        });

//        questionsExisted.forEach(r ->
//        {
//            Optional<TestQuestionEntity> existingDetail = getExistingQuestionsFromList(r, newQuestions);
//            if (existingDetail.isEmpty()) {
//                forDeleting.add(r);
//            }
//        });
        lesson.getQuestions().clear();
        lesson.getQuestions().addAll(newQuestions);

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
    public List<LessonEntity> getLessonByCourseId(Long courseId) {
        return lessonRepository.findByCourseCourseId(courseId);
    }


    private CourseEntity findByCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(""));
    }

    private TestQuestionEntity findByTestQuestionId(Long testQuestionId) {
        return testQuestionRepository.findById(testQuestionId).orElseThrow(() -> new EntityNotFoundException(""));
    }

    private Optional<TestQuestionEntity> getExistingQuestionsFromList(TestQuestionEntity dto, List<TestQuestionEntity> list) {
        return list.stream()
                .filter(testQuestion -> dto.getTestQuestionId() != null
                        && testQuestion.getTestQuestionId() == dto.getTestQuestionId())
                .findFirst();
    }

}
