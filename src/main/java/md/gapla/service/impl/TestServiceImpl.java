package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.dto.courseexam.ExamQuestionTaskTextDto;
import md.gapla.model.dto.courseexam.ExamTaskDto;
import md.gapla.model.dto.test.TestContentDto;
import md.gapla.model.dto.test.TestDto;
import md.gapla.model.dto.test.TestQuestionTypeTestTimeTypeDetailDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.courseexam.ExamQuestionTaskTextEntity;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.entity.test.*;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.test.TestInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.course.CourseRepository;
import md.gapla.repository.exam.ExamQuestionTaskTextRepository;
import md.gapla.repository.exam.ExamRepository;
import md.gapla.repository.test.*;
import md.gapla.service.TestService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static md.gapla.repository.specification.TestSpec.languageCodeEqual;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final AppMapper appMapper;
    private final TestRepository testRepository;
    private final CourseRepository courseRepository;

    private final LanguageRepository languageRepository;

    private final TestQuestionRepository testQuestionRepository;
    private final TestTextRepository testTextRepository;

    private final TestQuestionTypeTestTimeTypeRepository testQuestionTypeTestTimeTypeRepository;
    private final TestQuestionTypeLanguageRepository testQuestionTypeLanguageRepository;
    private final TestTimeTypeLanguageRepository testTimeTypeLanguageRepository;

    private final TestQuestionTypeTestTimeTypeDetailRepository testQuestionTypeTestTimeTypeDetailRepository;

    private final TestTimeTypeRepository testTimeTypeRepository;

    private final ExamQuestionTaskTextRepository examQuestionTaskTextRepository;

    private final ExamRepository examRepository;

    @Override
    public Page<TestDto> findTestPage(PageParamDto pageParamDto) {

//        List<TestQuestionTestEntity> testQuestionTestEntityList = testQuestionTestRepository.findAll();

        Map<String, String> params = pageParamDto.getParams();
        String languageCode = params.get("languageCode");


        Specification<TestEntity> specification = Specification
                .where(StringUtils.hasText(languageCode.toUpperCase()) ? languageCodeEqual(languageCode) : null);

        Page<TestEntity> list = testRepository.findAll(specification, pageParamDto.getPageRequest());

        return list.map(appMapper::map);
    }

    @Override
    public void deleteTestByTestId(Long testId) {
        TestEntity testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException(""));
        testEntity.setStatus(ObjectStatusEnum.DISABLE);
        testRepository.save(testEntity);
    }

    @Override
    @Transactional
    public TestDto addTest(TestInput testInput) {
        TestEntity testEntity = appMapper.map(testInput);
        TestTextEntity testText = testEntity.getTestText();

        LanguageEntity languageEntity = languageRepository.findByLanguageCode(testInput.getLanguageCode()).orElseThrow(() -> new EntityNotFoundException("Not found language by language code"));

        testEntity.setLanguage(languageEntity);
        testText = testTextRepository.save(testText);

        testEntity.setTestText(testText);
        testEntity.setTestTimeType(testTimeTypeRepository.findById(testInput.getTestTimeTypeId()).orElseThrow(() -> new EntityNotFoundException("Not found time type by id")));

        Set<TestQuestionEntity> questions = new HashSet<>();

        testInput.getQuestions().forEach(r -> {

            TestQuestionEntity testQuestionEntity = testQuestionRepository.findById(r)
                    .orElseThrow(() -> new EntityNotFoundException(""));
            questions.add(testQuestionEntity);
        });

        testEntity.getQuestions().addAll(questions);
        
        testEntity.setCreationDate(LocalDateTime.now());
        
        testEntity = testRepository.save(testEntity);

        return appMapper.map(testEntity);
    }

    @Override
    @Transactional
    public TestDto updateTest(TestInput testInput) {
        TestEntity testEntity = testRepository.findById(testInput.getTestId()).orElseThrow(() -> new EntityNotFoundException("Test with id = " + testInput.getTestId() + " not found."));
        List<TestQuestionEntity> existedQuestions = testEntity.getQuestions();
        LanguageEntity languageEntity = languageRepository.findByLanguageCode(testInput.getLanguageCode()).orElseThrow(() -> new EntityNotFoundException("Not found language by language code"));

        testEntity.setTestTimeType(testTimeTypeRepository.findById(testInput.getTestTimeTypeId()).orElseThrow(() -> new EntityNotFoundException("Not found time type by id")));
        testEntity.setLanguage(languageEntity);
        List<TestQuestionEntity> questions = new ArrayList<>();
        List<TestQuestionEntity> deletedQuestions = new ArrayList<>();

        TestTextEntity testText = testEntity.getTestText();
        if (testText != null)
            testText = testTextRepository.save(testText);
        testEntity.setTestText(testText);


        testInput.getQuestions().forEach(r -> {

            TestQuestionEntity testQuestionEntity = testQuestionRepository.findById(r)
                    .orElseThrow(() -> new EntityNotFoundException(""));
            questions.add(testQuestionEntity);
        });
        testEntity.getQuestions().clear();
        testEntity.getQuestions().addAll(questions);
        
        testEntity = testRepository.save(testEntity);

        return appMapper.map(testEntity);
    }

    @Override
    public TestContentDto findById(Long testId) {
        TestEntity testEntity = testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException(""));
        return mapToTestContentDto(testEntity);
    }

    @Override
    public ExamDto findExamById(Long examId) {
        ExamEntity examEntity = examRepository.findById(examId).orElseThrow(() -> new EntityNotFoundException("Not found Exam"));
        return fillExamToDto(examEntity);
    }

    @Override
    public ExamDto findByCourseId(String languageCode, Long courseId) {////????
        List<ExamEntity> test = courseRepository.findByCourseIdAndLanguageLanguageCode(courseId, languageCode).orElseThrow(() -> new EntityNotFoundException("")).getTests();

        Integer number = getRandoNumberTest(test.size());

        return fillExamToDto(test.get(number));
    }

    @Override
    public TestContentDto getTestByTestTimeAndLanguageCode(Long testTimeId, String languageCode) {
        List<TestEntity> allTests = testRepository.findByTestTimeTypeTestTimeTypeIdAndLanguageLanguageCode(testTimeId, languageCode);
        int testNumber = getRandoNumberTest(allTests.size());

        TestEntity testEntity = allTests.get(testNumber);

        return mapToTestContentDto(testEntity);
    }

    @Override
    public List<TestQuestionTypeTestTimeTypeDetailDto> getTestDetailInfo(Long testTimeId, String languageCode) {
        List<TestQuestionTypeTestTimeTypeDetailDto> lost = new ArrayList<>();

        List<TestQuestionTypeTestTimeTypeEntity> list = testQuestionTypeTestTimeTypeRepository.findByTestTimeTypeId(testTimeId);

        list.forEach(r -> {
            TestQuestionTypeLanguageEntity testQuestionTypeLanguageEntities = testQuestionTypeLanguageRepository.findByTestQuestionTypeTestQuestionTypeIdAndLanguageLanguageCode(r.getTestQuestionTypeId(), languageCode);
            TestTimeTypeLanguageEntity testTimeTypeLanguageEntities = testTimeTypeLanguageRepository.findByLanguageLanguageCodeAndTestTimeTypeTestTimeTypeId(languageCode, r.getTestTimeTypeId());

            List<TestQuestionTypeTestTimeTypeDetailEntity> detailEntities = testQuestionTypeTestTimeTypeDetailRepository.findByTestTimeTypeIdAndTestQuestionTypeIdAndLanguageLanguageCode(r.getTestTimeTypeId(), r.getTestQuestionTypeId(), languageCode);
            TestQuestionTypeTestTimeTypeDetailDto testQuestionTypeTestTimeTypeDetailDto = new TestQuestionTypeTestTimeTypeDetailDto();
            testQuestionTypeTestTimeTypeDetailDto.setTimeTypeValue(testTimeTypeLanguageEntities.getValue());
            testQuestionTypeTestTimeTypeDetailDto.setTypeQuestionValue(testQuestionTypeLanguageEntities.getValue());
            testQuestionTypeTestTimeTypeDetailDto.setDetails(detailEntities.stream().map(TestQuestionTypeTestTimeTypeDetailEntity::getValue).toList());
            lost.add(testQuestionTypeTestTimeTypeDetailDto);

        });
        return lost;
    }

    private Optional<TestQuestionEntity> getExistingTestQuestionFromList(TestQuestionEntity testQuestionId, List<TestQuestionEntity> list) {
        return list.stream()
                .filter(testQuestionEntity -> testQuestionEntity.getTestQuestionId().equals(testQuestionId.getTestQuestionId()))
                .findFirst();
    }

    private Integer getRandoNumber(Integer max) {
        return ThreadLocalRandom.current().nextInt(1, max);
    }

    private Integer getRandoNumberTest(Integer max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    private List<TestQuestionEntity> filterQuestionByType(Long typeId, List<TestQuestionEntity> list) {
        List<TestQuestionEntity> filtered = list.stream().filter(r -> r.getTestQuestionTypeLanguage().getTestQuestionType().getTestQuestionTypeId().equals(typeId)).toList();
        return filtered;
    }

    private TestContentDto mapToTestContentDto(TestEntity testEntity) {
        TestContentDto testContentDto = new TestContentDto();
        if (testEntity.getTestText() != null)
            testContentDto.setTestText(testEntity.getTestText().getValue());
        testContentDto.setTestId(testEntity.getTestId());
        testContentDto.setReading(filterQuestionByType(1L, testEntity.getQuestions()).stream().map(appMapper::map).toList());
        testContentDto.setListening(filterQuestionByType(2L, testEntity.getQuestions()).stream().map(appMapper::map).toList());
        return testContentDto;
    }

    private List<ExamQuestionTaskTextDto> fillTaskDto(List<ExamTaskEntity> list) {
        List<ExamQuestionTaskTextDto> examQuestionTaskTexts = new ArrayList<>();
        List<ExamTaskDto> examTaskDtoList = new ArrayList<>();
        list.stream().map(r -> {
            ExamTaskDto dto = new ExamTaskDto();
            List<ExamQuestionTaskTextEntity> examQuestionTaskTextEntities = examQuestionTaskTextRepository.findByExamTaskId(r.getExamTaskId());
            examQuestionTaskTexts.addAll(examQuestionTaskTextEntities.stream().map(appMapper::map).toList());
            return dto;
        }).toList();
        return examQuestionTaskTexts;
    }
    
    ExamDto fillExamToDto(ExamEntity testEntity) {

        ExamDto dto = new ExamDto();


        List<ExamTaskEntity> reading = testEntity.getTasks().stream().filter(r -> r.getTestQuestionTypeId() == 1L).toList();
        List<ExamTaskEntity> listening = testEntity.getTasks().stream().filter(r -> r.getTestQuestionTypeId() == 2L).toList();

        dto.setReading(reading.stream().map(appMapper::map).toList());
        dto.setListening(listening.stream().map(appMapper::map).toList());
        dto.setExamId(testEntity.getExamId());
        dto.setExamName(testEntity.getExamName());
        return dto;
    }
}
