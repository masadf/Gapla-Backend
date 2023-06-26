package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestQuestionTypeDetailDto;
import md.gapla.model.dto.test.TestQuestionTypeLanguageDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.test.TestQuestionTypeDetailEntity;
import md.gapla.model.entity.test.TestQuestionTypeEntity;
import md.gapla.model.entity.test.TestQuestionTypeLanguageEntity;
import md.gapla.model.input.test.TestQuestionTypeLanguageInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.test.TestQuestionTypeDetailRepository;
import md.gapla.repository.test.TestQuestionTypeLanguageRepository;
import md.gapla.repository.test.TestQuestionTypeRepository;
import md.gapla.service.TestQuestionTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static md.gapla.repository.specification.TestQuestionTypeSpec.languageCodeEqual;
import static md.gapla.repository.specification.TestQuestionTypeSpec.testQuestionTypeIdEqual;
import static md.gapla.util.ErrorMessagesUtils.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TestQuestionTypeServiceImpl implements TestQuestionTypeService {

    private final AppMapper appMapper;

    private final LanguageRepository languageRepository;
    private final TestQuestionTypeRepository testQuestionTypeRepository;
    private final TestQuestionTypeLanguageRepository testQuestionTypeLanguageRepository;
    private final TestQuestionTypeDetailRepository testQuestionTypeDetailRepository;


    @Override
    @Transactional
    public TestQuestionTypeLanguageDto addTestQuestionType(Long testQuestionTypeId, TestQuestionTypeLanguageInput testQuestionTypeLanguageInput) {
        TestQuestionTypeLanguageEntity testQuestionTypeLanguageEntity = testQuestionTypeLanguageRepository
                .findByTestQuestionTypeTestQuestionTypeIdAndLanguageLanguageCode(testQuestionTypeId, testQuestionTypeLanguageInput.getLanguageCode());

        if (testQuestionTypeLanguageEntity == null) {
            testQuestionTypeLanguageEntity = createTestQuestionType(testQuestionTypeId, testQuestionTypeLanguageInput);
        } else {
            updateTestQuestionType(testQuestionTypeId, testQuestionTypeLanguageEntity, testQuestionTypeLanguageInput);
        }
        return appMapper.map(testQuestionTypeLanguageEntity);
    }

    @Override
    public TestQuestionTypeLanguageDto updateTestQuestionType(Long testQuestionTypeId, TestQuestionTypeLanguageInput testQuestionTypeLanguageInput) {

        TestQuestionTypeLanguageEntity testQuestionTypeLanguageEntity = testQuestionTypeLanguageRepository
                .findByTestQuestionTypeTestQuestionTypeIdAndLanguageLanguageCode(testQuestionTypeId, testQuestionTypeLanguageInput.getLanguageCode());

        if (testQuestionTypeLanguageEntity == null) {
            testQuestionTypeLanguageEntity = createTestQuestionType(testQuestionTypeId, testQuestionTypeLanguageInput);
        } else {
            updateTestQuestionType(testQuestionTypeId, testQuestionTypeLanguageEntity, testQuestionTypeLanguageInput);
        }
        return appMapper.map(testQuestionTypeLanguageEntity);
    }

    @Override
    public Page<TestQuestionTypeLanguageDto> getTestQuestionTypePage(PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();
        String testQuestionTypeId = params.get("testQuestionTypeId");
        String languageCode = params.get("languageCode");

        Specification<TestQuestionTypeLanguageEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode.toUpperCase()) : null)
                .and(StringUtils.hasText(testQuestionTypeId) ? testQuestionTypeIdEqual(Long.valueOf(testQuestionTypeId)) : null);
        Page<TestQuestionTypeLanguageEntity> testQuestionTypeLanguages = testQuestionTypeLanguageRepository
                .findAll(specification, pageParamDto.getPageRequest());

        return testQuestionTypeLanguages.map(appMapper::map);
    }

    @Override
    public List<TestQuestionTypeLanguageDto> getTestQuestionTypeList(String languageCode, Long testQuestionTypeId) {
        Specification<TestQuestionTypeLanguageEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode.toUpperCase()) : null)
                .and(testQuestionTypeId != null ? testQuestionTypeIdEqual(testQuestionTypeId) : null);

        List<TestQuestionTypeLanguageEntity> testQuestionTypeLanguages = testQuestionTypeLanguageRepository
                .findAll(specification);

        return testQuestionTypeLanguages.stream().map(appMapper::map).toList();
    }


    private TestQuestionTypeLanguageEntity createTestQuestionType(Long testQuestionTypeId, TestQuestionTypeLanguageInput testQuestionTypeLanguageInput) {
        TestQuestionTypeLanguageEntity testQuestionTypeLanguageEntity = new TestQuestionTypeLanguageEntity();

        TestQuestionTypeEntity testQuestionType = testQuestionTypeRepository.findById(testQuestionTypeId)
                .orElseThrow(() -> new EntityNotFoundException(""));

        LanguageEntity language = findLanguageByCode(testQuestionTypeLanguageInput.getLanguageCode());


        testQuestionTypeLanguageEntity.setTestQuestionType(testQuestionType);
        testQuestionTypeLanguageEntity.setLanguage(language);
        testQuestionTypeLanguageEntity.setValue(testQuestionTypeLanguageInput.getValue());

        testQuestionTypeLanguageEntity = testQuestionTypeLanguageRepository.save(testQuestionTypeLanguageEntity);
        if (!testQuestionTypeLanguageInput.getDetails().isEmpty()) {
            Long testQuestionTypeLanguageId = testQuestionTypeLanguageEntity.getTestQuestionTypeLanguageId();
            List<TestQuestionTypeDetailEntity> list = testQuestionTypeLanguageInput.getDetails().stream().map(appMapper::map).toList();
            list.forEach(r -> {
                r.setTestQuestionTypeLanguageId(testQuestionTypeLanguageId);
            });
            testQuestionTypeLanguageEntity.getDetails().addAll(list);
            testQuestionTypeLanguageEntity = testQuestionTypeLanguageRepository.save(testQuestionTypeLanguageEntity);
        }
        return testQuestionTypeLanguageEntity;
    }

    private TestQuestionTypeLanguageEntity updateTestQuestionType(Long testQuestionTypeId, TestQuestionTypeLanguageEntity testQuestionTypeLanguageEntity, TestQuestionTypeLanguageInput testQuestionTypeLanguageInput) {
        List<TestQuestionTypeDetailEntity> existedDetails = testQuestionTypeLanguageEntity.getDetails();
        List<TestQuestionTypeDetailEntity> newDetails = new ArrayList<>();
        List<TestQuestionTypeDetailDto> details = testQuestionTypeLanguageInput.getDetails();
        List<TestQuestionTypeDetailEntity> deletedDetails = new ArrayList<>();

        testQuestionTypeLanguageEntity.setValue(testQuestionTypeLanguageInput.getValue());

        Long testQuestionTypeLanguageId = testQuestionTypeLanguageEntity.getTestQuestionTypeLanguageId();
        details.forEach(r ->
        {
            Optional<TestQuestionTypeDetailEntity> existingTestQuestionTypeDetailEntity = getExistingTestQuestionTypeDetailFromList(r, existedDetails);
            if (existingTestQuestionTypeDetailEntity.isEmpty()) {
                createTestQuestionTypeDetailEntity(testQuestionTypeLanguageId, r, newDetails);
            } else {
                updateTestQuestionTypeDetailEntity(existingTestQuestionTypeDetailEntity.get(), r, newDetails);
            }
        });

        existedDetails.forEach(r ->
        {
            Optional<TestQuestionTypeDetailEntity> existingDetail = getExistingTestQuestionTypeDetailFromList(r, newDetails);
            if (existingDetail.isEmpty()) {
                deletedDetails.add(r);
            }
        });
        testQuestionTypeDetailRepository.deleteAll(deletedDetails);
        testQuestionTypeLanguageEntity.getDetails().clear();
        testQuestionTypeLanguageEntity.getDetails().addAll(newDetails);

        testQuestionTypeLanguageEntity = testQuestionTypeLanguageRepository.save(testQuestionTypeLanguageEntity);

        return testQuestionTypeLanguageEntity;
    }


    private LanguageEntity findLanguageByCode(String languageCode) {
        LanguageEntity language = languageRepository.findByLanguageCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, languageCode)));

        return language;
    }

    private Optional<TestQuestionTypeDetailEntity> getExistingTestQuestionTypeDetailFromList(TestQuestionTypeDetailDto dto, List<TestQuestionTypeDetailEntity> list) {
        return list.stream()
                .filter(testQuestionTypeDetailEntity -> testQuestionTypeDetailEntity.getValue().toUpperCase().equals(dto.getValue().toUpperCase())
                        && testQuestionTypeDetailEntity.getTestQuestionTypeDetailId() == dto.getTestQuestionTypeDetailId())
                .findFirst();
    }

    private Optional<TestQuestionTypeDetailEntity> getExistingTestQuestionTypeDetailFromList(TestQuestionTypeDetailEntity dto, List<TestQuestionTypeDetailEntity> list) {
        return list.stream()
                .filter(testQuestionTypeDetailEntity -> testQuestionTypeDetailEntity.getValue().toUpperCase().equals(dto.getValue().toUpperCase())
                        && testQuestionTypeDetailEntity.getTestQuestionTypeDetailId() == dto.getTestQuestionTypeDetailId())
                .findFirst();
    }

    private void createTestQuestionTypeDetailEntity(Long testQuestionTypeLanguageId, TestQuestionTypeDetailDto testQuestionTypeDetailDto, List<TestQuestionTypeDetailEntity> details) {
        TestQuestionTypeDetailEntity testQuestionTypeDetailEntity = new TestQuestionTypeDetailEntity();
        testQuestionTypeDetailEntity.setValue(testQuestionTypeDetailDto.getValue());
        testQuestionTypeDetailEntity.setTestQuestionTypeLanguageId(testQuestionTypeLanguageId);
        details.add(testQuestionTypeDetailEntity);
    }

    private void updateTestQuestionTypeDetailEntity(TestQuestionTypeDetailEntity testQuestionTypeDetailEntity, TestQuestionTypeDetailDto testQuestionTypeDetailDto, List<TestQuestionTypeDetailEntity> details) {
        testQuestionTypeDetailEntity.setValue(testQuestionTypeDetailDto.getValue());
        details.add(testQuestionTypeDetailEntity);
    }
}
