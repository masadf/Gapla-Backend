package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonMaterialsDto;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.lesson.LessonMaterialsInput;
import md.gapla.repository.lesson.LessonMaterialsRepository;
import md.gapla.repository.lesson.LessonMaterialsTypeRepository;
import md.gapla.repository.lesson.LessonRepository;
import md.gapla.service.LessonMaterialsService;
import md.gapla.service.S3BucketService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static md.gapla.repository.specification.LessonMaterialsSpec.*;

@Service
@RequiredArgsConstructor
public class LessonMaterialsServiceImpl implements LessonMaterialsService {

    private final AppMapper appMapper;

    private final S3BucketService s3BucketService;

    private final LessonMaterialsRepository lessonMaterialsRepository;

    private final LessonRepository lessonRepository;
    private final LessonMaterialsTypeRepository lessonMaterialsTypeRepository;


    @Override
    public Page<LessonMaterialsDto> getLessonMaterialsPage(PageParamDto pageParamDto) {

        Map<String, String> params = pageParamDto.getParams();
        String lessonMaterialsTypeId = params.get("lessonMaterialsTypeId");
        String lessonId = params.get("lessonId");
        String documentName = params.get("documentName");


        Specification<LessonMaterialsEntity> specification = Specification
                .where(StringUtils.hasText(lessonMaterialsTypeId) ? lessonMaterialsTypeIdEqual(Long.valueOf(lessonMaterialsTypeId)) : null)
                .and(StringUtils.hasText(lessonId) ? lessonIdEqual(Long.valueOf(lessonId)) : null)
                .and(StringUtils.hasText(documentName) ? likeDocumentName(documentName.toUpperCase()) : null);

        Page<LessonMaterialsEntity> list = lessonMaterialsRepository.findAll(specification, pageParamDto.getPageRequest());

        return list.map(appMapper::map);
    }

    @Override
    public List<LessonMaterialsDto> getLessonMaterialsList(String languageCode, PageParamDto pageParamDto) {

        Specification<LessonMaterialsEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode) : null);
        return null;
    }

    @Override
    public LessonMaterialsDto addLessonMaterials(LessonMaterialsInput input) {

        LessonMaterialsEntity lessonMaterialsEntity = fillField(new LessonMaterialsEntity(), input);

        lessonMaterialsEntity = lessonMaterialsRepository.save(lessonMaterialsEntity);

        return appMapper.map(lessonMaterialsEntity);
    }

    @Override
    public LessonMaterialsDto uploadLessonMaterials(Long documentId, MultipartFile file) {
        LessonMaterialsEntity lessonMaterialsEntity = lessonMaterialsRepository.findById(documentId).orElseThrow(() -> new EntityNotFoundException(""));
        String filePathName = UUID.randomUUID() + file.getOriginalFilename();
        lessonMaterialsEntity.setFilePathName(filePathName);

        String s3FileAccessUrl = s3BucketService.uploadFileToS3(file, lessonMaterialsEntity, filePathName);

        lessonMaterialsEntity.setDocumentName(file.getOriginalFilename());
        lessonMaterialsEntity.setUrlDocument(s3FileAccessUrl);
        lessonMaterialsEntity.setStatus(ObjectStatusEnum.ENABLE);
        lessonMaterialsEntity = lessonMaterialsRepository.save(lessonMaterialsEntity);

        return appMapper.map(lessonMaterialsEntity);
    }

    @Override
    public byte[] downloadLessonMaterials(Long documentId) {
        LessonMaterialsEntity lessonMaterialsEntity = lessonMaterialsRepository.findById(documentId).orElseThrow(() -> new EntityNotFoundException(""));
        return s3BucketService.downloadFileFromS3(lessonMaterialsEntity.getFilePathName());
    }

    @Override
    public LessonMaterialsDto updateLessonMaterials(LessonMaterialsInput input) {
        LessonMaterialsEntity lessonMaterialsEntity = lessonMaterialsRepository.findById(input.getLessonMaterialId()).orElseThrow(() -> new EntityNotFoundException(""));
        lessonMaterialsEntity = fillField(lessonMaterialsEntity, input);
        lessonMaterialsEntity = lessonMaterialsRepository.save(lessonMaterialsEntity);

        return appMapper.map(lessonMaterialsEntity);
    }

    @Override
    public LessonMaterialsDto getLessonMaterials(Long documentId) {

        LessonMaterialsEntity lessonMaterialsEntity = lessonMaterialsRepository.findById(documentId).orElseThrow(() -> new EntityNotFoundException(""));


        return appMapper.map(lessonMaterialsEntity);
    }

    @Override
    public void deleteLessonMaterials(Long documentId) {

        LessonMaterialsEntity lessonMaterialsEntity = lessonMaterialsRepository.findById(documentId).orElseThrow(() -> new EntityNotFoundException(""));
        lessonMaterialsEntity.setStatus(ObjectStatusEnum.DISABLE);
        lessonMaterialsRepository.save(lessonMaterialsEntity);
    }

    private LessonMaterialsDto fillField(String languageCode, LessonMaterialsEntity lessonMaterialsEntity) {
        return null;
    }

    private LessonMaterialsEntity fillField(LessonMaterialsEntity lessonMaterialsEntity, LessonMaterialsInput input) {
        if (input.getLessonId() != null) {
            LessonEntity lesson = lessonRepository.findById(input.getLessonId()).orElseThrow(() -> new EntityNotFoundException(""));
            lessonMaterialsEntity.setLesson(lesson);
        }

        LessonMaterialsTypeEntity lessonMaterialsType = lessonMaterialsTypeRepository.findById(input.getLessonMaterialsTypeId()).orElseThrow(() -> new EntityNotFoundException(""));
        lessonMaterialsEntity.setLessonMaterialsType(lessonMaterialsType);
        return lessonMaterialsEntity;
    }
}
