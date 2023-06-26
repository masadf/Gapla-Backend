package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.test.TestTimeTypeLanguageDto;
import md.gapla.model.dto.view.TimeTypeViewDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.test.TestTimeTypeDetailEntity;
import md.gapla.model.entity.test.TestTimeTypeEntity;
import md.gapla.model.entity.test.TestTimeTypeLanguageEntity;
import md.gapla.model.entity.view.TimeTypeViewEntity;
import md.gapla.model.input.test.TestTimeTypeLanguageInput;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.specification.filters.TestTimeViewSpec;
import md.gapla.repository.test.TestTimeTypeDetailRepository;
import md.gapla.repository.test.TestTimeTypeLanguageRepository;
import md.gapla.repository.test.TestTimeTypeRepository;
import md.gapla.repository.view.TestTimeViewRepository;
import md.gapla.service.TimeTestTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static md.gapla.repository.specification.TimeTestTypeSpec.languageCodeEqual;
import static md.gapla.repository.specification.TimeTestTypeSpec.testTimeTypeIdEqual;
import static md.gapla.util.ErrorMessagesUtils.LANGUAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TimeTestTypeServiceImpl implements TimeTestTypeService {

    private final AppMapper appMapper;
    private final TestTimeTypeLanguageRepository testTimeTypeLanguageRepository;
    private final TestTimeTypeDetailRepository testTimeTypeDetailRepository;
    private final TestTimeTypeRepository testTimeTypeRepository;
    private final LanguageRepository languageRepository;

    private final TestTimeViewRepository testTimeViewRepository;

    @Override
    public Page<TimeTypeViewDto> findAllTestTimeType(PageParamDto pageParamDto) {
        Specification<TimeTypeViewEntity> masterSpec = null;
        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new TestTimeViewSpec(filterCriteria));
        }
        Page<TimeTypeViewEntity> pages = testTimeViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());
        return pages.map(appMapper::map);
    }

    @Override
    public Page<TestTimeTypeLanguageDto> findAllTestTimeTypeLanguageAndId(Long id, String languageCode, PageParamDto pageParamDto) {

        Map<String, String> params = pageParamDto.getParams();

        Specification<TestTimeTypeLanguageEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode) : null)
                .and(id != null ? testTimeTypeIdEqual(id) : null);

        Page<TestTimeTypeLanguageEntity> testTimeTypeLanguages = testTimeTypeLanguageRepository.findAll(specification, pageParamDto.getPageRequest());


        return testTimeTypeLanguages.map(appMapper::map);
    }

    @Override
    public TestTimeTypeLanguageDto finTestTimeTypeLanguageById(String languageCode, Long timeTypeTestId) {
        TestTimeTypeLanguageEntity testTimeTypeLanguages = testTimeTypeLanguageRepository
                .findByLanguageLanguageCodeAndTestTimeTypeTestTimeTypeId(languageCode, timeTypeTestId);
        return appMapper.map(testTimeTypeLanguages);
    }

    @Override
    @Transactional
    public TestTimeTypeLanguageDto addNewTestTimeTypeLanguage(Long id, TestTimeTypeLanguageInput testTimeTypeLanguageInput) {

        TestTimeTypeLanguageEntity testTimeTypeLanguage = testTimeTypeLanguageRepository.findByLanguageLanguageCodeAndTestTimeTypeTestTimeTypeId(testTimeTypeLanguageInput.getLanguageCode(), id);
        if (testTimeTypeLanguage != null) {
            testTimeTypeLanguage = updateExistedTestTimeTypeLanguage(id, testTimeTypeLanguage, testTimeTypeLanguageInput);
        } else {
            testTimeTypeLanguage = createNewTestTimeTypeLanguage(id, testTimeTypeLanguageInput);
        }
        return appMapper.map(testTimeTypeLanguage);
    }

    @Override
    @Transactional
    public TestTimeTypeLanguageDto updateExistedTestTimeTypeLanguage(Long id, TestTimeTypeLanguageInput testTimeTypeLanguageInput) {

        TestTimeTypeLanguageEntity testTimeTypeLanguage = testTimeTypeLanguageRepository.findByLanguageLanguageCodeAndTestTimeTypeTestTimeTypeId(testTimeTypeLanguageInput.getLanguageCode(), id);

        //TODO FINISH UPDATE
        if (testTimeTypeLanguage != null) {
            testTimeTypeLanguage = updateExistedTestTimeTypeLanguage(id, testTimeTypeLanguage, testTimeTypeLanguageInput);
        } else {
            testTimeTypeLanguage = createNewTestTimeTypeLanguage(id, testTimeTypeLanguageInput);
        }
        return appMapper.map(testTimeTypeLanguage);
    }

    private TestTimeTypeLanguageEntity createNewTestTimeTypeLanguage(Long id, TestTimeTypeLanguageInput testTimeTypeLanguageInput) {
        TestTimeTypeLanguageEntity testTimeTypeLanguage = new TestTimeTypeLanguageEntity();

        LanguageEntity language = languageRepository.findByLanguageCode(testTimeTypeLanguageInput.getLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, testTimeTypeLanguageInput.getLanguageCode())));

        TestTimeTypeEntity testTimeType = testTimeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));

        testTimeTypeLanguage.setLanguage(language);
        testTimeTypeLanguage.setTestTimeType(testTimeType);
        testTimeTypeLanguage.setValue(testTimeTypeLanguageInput.getValue());

        List<TestTimeTypeDetailEntity> details = testTimeTypeLanguageInput.getDetails().stream().map(appMapper::map).toList();

        testTimeTypeLanguage = testTimeTypeLanguageRepository.save(testTimeTypeLanguage);

        if (!details.isEmpty()) {
            Long testTimeTypeLanguageId = testTimeTypeLanguage.getTestTimeTypeLanguageId();

            details.forEach(r -> {
                r.setTestTimeTypeLanguageId(testTimeTypeLanguageId);
            });

            testTimeTypeLanguage.getDetails().addAll(details);
            testTimeTypeLanguage = testTimeTypeLanguageRepository.save(testTimeTypeLanguage);
        }

        return testTimeTypeLanguage;
    }

    private TestTimeTypeLanguageEntity updateExistedTestTimeTypeLanguage(Long id, TestTimeTypeLanguageEntity testTimeTypeLanguage, TestTimeTypeLanguageInput testTimeTypeLanguageInput) {
        testTimeTypeLanguage.setValue(testTimeTypeLanguageInput.getValue());

        List<TestTimeTypeDetailEntity> details = testTimeTypeLanguageInput.getDetails().stream().map(appMapper::map).toList();
        List<TestTimeTypeDetailEntity> detailsExisted = testTimeTypeLanguage.getDetails();
        List<TestTimeTypeDetailEntity> newDetails = new ArrayList<>();
        List<TestTimeTypeDetailEntity> forDeleting = new ArrayList<>();

        Long testTimeTypeLanguageId = testTimeTypeLanguage.getTestTimeTypeLanguageId();

        details.forEach(r ->
        {
            Optional<TestTimeTypeDetailEntity> existingDetail = getExistingTestTimeTypeDetailFromList(r, detailsExisted);
            if (existingDetail.isPresent()) {
                updateTestTimeTypeDetailEntity(newDetails, existingDetail.get(), r);
            } else {
                createTestTimeTypeDetailEntity(newDetails, r, testTimeTypeLanguageId);
            }
        });

        detailsExisted.forEach(r ->
        {
            Optional<TestTimeTypeDetailEntity> existingDetail = getExistingTestTimeTypeDetailFromList(r, newDetails);
            if (existingDetail.isEmpty()) {
                forDeleting.add(r);
            }
        });
        testTimeTypeDetailRepository.deleteAll(forDeleting);

        testTimeTypeLanguage.getDetails().clear();

        testTimeTypeLanguage.getDetails().addAll(newDetails);

        testTimeTypeLanguage = testTimeTypeLanguageRepository.save(testTimeTypeLanguage);

        return testTimeTypeLanguage;
    }

    private void createTestTimeTypeDetailEntity(List<TestTimeTypeDetailEntity> details, TestTimeTypeDetailEntity newDetail, Long testTimeTypeLanguageId) {
        newDetail.setTestTimeTypeLanguageId(testTimeTypeLanguageId);
        details.add(newDetail);
    }

    private void updateTestTimeTypeDetailEntity(List<TestTimeTypeDetailEntity> details, TestTimeTypeDetailEntity existingDetail, TestTimeTypeDetailEntity newDetail) {
        details.add(existingDetail);
    }

    private Optional<TestTimeTypeDetailEntity> getExistingTestTimeTypeDetailFromList(TestTimeTypeDetailEntity dto, List<TestTimeTypeDetailEntity> testTimeTypeDetailEntities) {
        return testTimeTypeDetailEntities.stream()
                .filter(testTimeTypeDetail -> testTimeTypeDetail.getValue().toUpperCase().equals(dto.getValue().toUpperCase())
                        && testTimeTypeDetail.getTestTimeTypeDetailId() == dto.getTestTimeTypeDetailId())
                .findFirst();
    }
}
