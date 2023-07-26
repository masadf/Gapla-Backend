package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestAnswerDto;
import md.gapla.model.dto.test.TestQuestionDto;
import md.gapla.model.entity.test.TestAnswerEntity;
import md.gapla.model.entity.test.TestQuestionEntity;
import md.gapla.model.entity.test.TestQuestionTypeLanguageEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.test.TestQuestionInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.test.TestAnswerRepository;
import md.gapla.repository.test.TestQuestionRepository;
import md.gapla.repository.test.TestQuestionTypeLanguageRepository;
import md.gapla.service.TestQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static md.gapla.repository.specification.TestQuestionSpec.languageCodeEqual;
import static md.gapla.repository.specification.TestQuestionSpec.testQuestionTypeIdEqual;

@Service
@RequiredArgsConstructor
public class TestQuestionServiceImpl implements TestQuestionService {

    private final AppMapper appMapper;
    private final TestQuestionRepository testQuestionRepository;

    private final LanguageRepository languageRepository;

    private final TestQuestionTypeLanguageRepository testQuestionTypeLanguageRepository;

    private final TestAnswerRepository testAnswerRepository;

    @Override
    public TestQuestionDto addTestQuestion(Long testQuestionTypeId, TestQuestionInput testQuestionInput) {
        TestQuestionEntity testQuestionEntity = new TestQuestionEntity();
        TestQuestionTypeLanguageEntity testQuestionTypeLanguage = testQuestionTypeLanguageRepository.findByTestQuestionTypeTestQuestionTypeIdAndLanguageLanguageCode(testQuestionTypeId, testQuestionInput.getLanguageCode());

        if (testQuestionTypeLanguage == null) {
            throw new EntityNotFoundException("");
        }
        testQuestionEntity.setTestQuestionTypeLanguage(testQuestionTypeLanguage);
        testQuestionEntity.setValue(testQuestionInput.getValue());
        testQuestionEntity.setQuestionType(testQuestionInput.getQuestionType());
        testQuestionEntity.setAudioValue(testQuestionInput.getAudioValue());
        testQuestionEntity = testQuestionRepository.save(testQuestionEntity);

        if (!testQuestionInput.getVariants().isEmpty()) {
            Long testQuestionId = testQuestionEntity.getTestQuestionId();

            List<TestAnswerEntity> variants = testQuestionInput.getVariants().stream().map(appMapper::map).toList();

            variants.forEach(r -> {
                r.setTestQuestionId(testQuestionId);
            });

            testQuestionEntity.getVariants().addAll(variants);

            testQuestionEntity = testQuestionRepository.save(testQuestionEntity);
        }
        return appMapper.map(testQuestionEntity);
    }

    @Override
    public TestQuestionDto updateTestQuestion(Long testQuestionTypeId, TestQuestionInput testQuestionInput) {
        TestQuestionEntity testQuestionEntity = testQuestionRepository.findById(testQuestionInput.getTestQuestionId())
                .orElseThrow(() -> new EntityNotFoundException(""));
        testQuestionEntity.setAudioValue(testQuestionInput.getAudioValue());
        List<TestAnswerDto> variants = testQuestionInput.getVariants();
        List<TestAnswerEntity> existedVariants = testQuestionEntity.getVariants();
        List<TestAnswerEntity> newVariants = new ArrayList<>();
        List<TestAnswerEntity> deletingVariants = new ArrayList<>();
        Long testQuestionId = testQuestionEntity.getTestQuestionId();
        variants.forEach(r -> {
            Optional<TestAnswerEntity> testAnswerEntity = getExistingTestAnswerFromList(r, existedVariants);
            if (testAnswerEntity.isEmpty()) {
                createTestAnswerEntity(testQuestionId, r, newVariants);
            } else {
                updateTestAnswerEntity(testAnswerEntity.get(), r, newVariants);
            }
        });
        existedVariants.forEach(r -> {
            Optional<TestAnswerEntity> testAnswerEntity = getExistingTestAnswerFromList(r, newVariants);
            if (testAnswerEntity.isPresent()) {
                deletingVariants.add(testAnswerEntity.get());
            }
        });
        testAnswerRepository.deleteAll(deletingVariants);

        testQuestionEntity.getVariants().clear();

        testQuestionEntity.getVariants().addAll(newVariants);


        testQuestionEntity.setValue(testQuestionInput.getValue());

        testQuestionEntity = testQuestionRepository.save(testQuestionEntity);

        return appMapper.map(testQuestionEntity);
    }

    @Override
    public Page<TestQuestionDto> getTestQuestionPage(PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();
        String languageCode = params.get("languageCode");
        String testQuestionTypeId = params.get("testQuestionTypeId");

        Specification<TestQuestionEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode) : null)
                .and(StringUtils.hasText(testQuestionTypeId) ? testQuestionTypeIdEqual(Long.valueOf(testQuestionTypeId)) : null);

        Page<TestQuestionEntity> list = testQuestionRepository.findAll(specification, pageParamDto.getPageRequest());

        return list.map(appMapper::map);
    }

    @Override
    public List<TestQuestionDto> getTestQuestionList(String languageCode, PageParamDto pageParamDto) {

        Map<String, String> params = pageParamDto.getParams();

        String testQuestionTypeId = params.get("testQuestionTypeId");

        Specification<TestQuestionEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode) : null)
                .and(StringUtils.hasText(testQuestionTypeId) ? testQuestionTypeIdEqual(Long.valueOf(testQuestionTypeId)) : null);

        List<TestQuestionEntity> list = testQuestionRepository.findAll(specification);

        return list.stream().map(appMapper::map).toList();
    }

    @Override
    public void deleteTestQuestionByTestQuestionId(Long testQuestionId) {
        TestQuestionEntity testQuestionEntity = testQuestionRepository.findById(testQuestionId)
                .orElseThrow(() -> new EntityNotFoundException(""));

        testQuestionEntity.setStatus(ObjectStatusEnum.DISABLE);
        testQuestionRepository.save(testQuestionEntity);

    }

    private Optional<TestAnswerEntity> getExistingTestAnswerFromList(TestAnswerDto dto, List<TestAnswerEntity> list) {
        return list.stream()
                .filter(testAnswerEntity -> testAnswerEntity.getTestAnswerId() != null
                        && testAnswerEntity.getTestAnswerId() == dto.getTestAnswerId())
                .findFirst();
    }

    private Optional<TestAnswerEntity> getExistingTestAnswerFromList(TestAnswerEntity entity, List<TestAnswerEntity> list) {
        return list.stream()
                .filter(testAnswerEntity -> testAnswerEntity.getTestAnswerId() != null
                        && testAnswerEntity.getTestAnswerId() == entity.getTestAnswerId())
                .findFirst();
    }

    private void createTestAnswerEntity(Long testQuestionId, TestAnswerDto testAnswerDto, List<TestAnswerEntity> newVariants) {
        TestAnswerEntity testAnswerEntity = new TestAnswerEntity();
        testAnswerEntity.setValue(testAnswerDto.getValue());
        testAnswerEntity.setIsCorrect(testAnswerDto.getIsCorrect());
        testAnswerEntity.setTestQuestionId(testQuestionId);
        newVariants.add(testAnswerEntity);
    }

    private void updateTestAnswerEntity(TestAnswerEntity testAnswerEntity, TestAnswerDto testAnswerDto, List<TestAnswerEntity> newVariants) {
        testAnswerEntity.setValue(testAnswerDto.getValue());
        testAnswerEntity.setIsCorrect(testAnswerDto.getIsCorrect());
        newVariants.add(testAnswerEntity);
    }

    public TestQuestionDto getTestQuestion(Long testQuestionId){
        return appMapper.map(testQuestionRepository.findById(testQuestionId).orElseThrow(() -> new EntityNotFoundException("")));
    }
}
