package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.exception.InvalidRequestException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumDto;
import md.gapla.model.dto.forum.ForumTypeDetailDto;
import md.gapla.model.dto.forum.ForumTypeDto;
import md.gapla.model.dto.view.ForumTypeViewDto;
import md.gapla.model.dto.view.ForumsViewDto;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.forum.ForumEntity;
import md.gapla.model.entity.forum.ForumTypeDetailEntity;
import md.gapla.model.entity.forum.ForumTypeEntity;
import md.gapla.model.entity.view.ForumTypeViewEntity;
import md.gapla.model.entity.view.ForumsViewEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.forum.ForumInput;
import md.gapla.model.input.forum.ForumTypeCreateInput;
import md.gapla.model.input.forum.ForumTypeDetailCreateInput;
import md.gapla.model.input.forum.ForumUpdate;
import md.gapla.repository.LanguageRepository;
import md.gapla.repository.forum.*;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.specification.filters.ForumTypeViewSpec;
import md.gapla.repository.specification.filters.ForumViewSpec;
import md.gapla.repository.view.ForumTypeViewRepository;
import md.gapla.repository.view.ForumViewRepository;
import md.gapla.service.ForumService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static md.gapla.repository.specification.ForumTypeDetailSpec.forumTypeIdEqual;
import static md.gapla.repository.specification.ForumTypeDetailSpec.languageCodeEqual;
import static md.gapla.util.ErrorMessagesUtils.*;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
    private final AppMapper appMapper;

    private final LanguageRepository languageRepository;
    private final ForumRepository forumRepository;
    private final ForumTypeRepository forumTypeRepository;
    private final ForumTypeDetailRepository forumTypeDetailRepository;
    private final ForumQuestionsRepository forumQuestionsRepository;
    private final ForumQuestionsCommentsRepository forumQuestionsCommentsRepository;

    private final ForumTypeViewRepository forumTypeViewRepository;
    private final ForumViewRepository forumViewRepository;


    @Override
    @Transactional
    public ForumTypeDto addForumType(ForumTypeCreateInput forumTypeDto) {

        return verifyExistingForumTypeDto(forumTypeDto);
    }

    @Override
    public ForumTypeDto updateForumType(ForumTypeCreateInput forumTypeDto) {
        return verifyExistingForumTypeDto(forumTypeDto);
    }

    @Override
    public void saveForumTypeDetail(ForumTypeDetailCreateInput input) {
        Optional<ForumTypeDetailEntity> forumTypeDetailEntity = forumTypeDetailRepository
                .findByForumTypeIdAndValueIgnoreCaseAndLanguageLanguageCodeIgnoreCase(input.getForumTypeId(), input.getValue(), input.getLanguageCode());

        if (forumTypeDetailEntity.isPresent()) {
            throw new InvalidRequestException("Уже существДует");
        }

        forumTypeDetailRepository.save(createForumTypeDetailEntity(input));
    }

    @Override
    public void updateForumTypeDetail(ForumTypeDetailCreateInput input) {
        ForumTypeDetailEntity forumTypeDetailEntity = forumTypeDetailRepository.findById(input.getForumTypeDetailId())
                .orElseThrow(() -> new EntityNotFoundException("Не существует"));

        Optional<ForumTypeDetailEntity> isExistedForumTypeDetailEntity = forumTypeDetailRepository
                .findByForumTypeIdAndLanguageLanguageCodeIgnoreCase(input.getForumTypeId(), input.getLanguageCode());

        if (isExistedForumTypeDetailEntity.isPresent()
                && !isExistedForumTypeDetailEntity.get().getForumTypeDetailId().equals(forumTypeDetailEntity.getForumTypeDetailId())) {
            throw new InvalidRequestException("Уже существует");
        }
        forumTypeDetailRepository.save(createForumTypeDetailEntity(input, forumTypeDetailEntity));
    }

    @Override
    @Transactional
    public void deleteForumTypeById(Long forumTypeId) {
        ForumTypeEntity forumTypeEntity = forumTypeRepository.findById(forumTypeId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_TYPE_DETAIL_NOT_FOUND, forumTypeId)));
        forumTypeEntity.setStatus(ObjectStatusEnum.DISABLE);
        forumTypeRepository.save(forumTypeEntity);

        forumRepository.findByForumTypeDetailForumTypeId(forumTypeId).forEach(r ->
        {
            r.setStatus(ObjectStatusEnum.DISABLE);
            forumRepository.save(r);
        });

    }

    @Override
    public Page<ForumTypeDto> getForumTypePage(PageParamDto pageParamDto) {

        Page<ForumTypeDto> forumTypeDtos = forumTypeRepository.findAll(pageParamDto.getPageRequest()).map(appMapper::map);
        return forumTypeDtos;
    }

    @Override
    public Page<ForumTypeViewDto> getForumTypeViewPage(PageParamDto pageParamDto) {
        Specification<ForumTypeViewEntity> masterSpec = null;
        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new ForumTypeViewSpec(filterCriteria));
        }
        Page<ForumTypeViewEntity> pages=forumTypeViewRepository.findAll(masterSpec,pageParamDto.getPageRequest());
        return pages.map(appMapper::map);
    }

    @Override
    public List<ForumTypeViewDto> getForumTypeViewList(PageParamDto pageParamDto) {
        Specification<ForumTypeViewEntity> masterSpec = null;
        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new ForumTypeViewSpec(filterCriteria));
        }

        List<ForumTypeViewEntity> list=forumTypeViewRepository.findAll(masterSpec);

        return list.stream().map(appMapper::map).toList();
    }

    @Override
    public Page<ForumTypeDetailDto> getForumTypeDetailPage(PageParamDto pageParamDto) {

        Map<String, String> params = pageParamDto.getParams();
        String languageCode = params.get("languageCode");
        String forumTypeId = params.get("forumTypeId");

        Specification<ForumTypeDetailEntity> specification = Specification
                .where(StringUtils.hasText(languageCode) ? languageCodeEqual(languageCode) : null)
                .and(StringUtils.hasText(forumTypeId) ? forumTypeIdEqual(Long.valueOf(forumTypeId)) : null);

        Page<ForumTypeDetailEntity> list = forumTypeDetailRepository.findAll(specification, pageParamDto.getPageRequest());


        return list.map(appMapper::map);
    }

    @Override
    public Page<ForumsViewDto> getForumDtoPage(PageParamDto pageParamDto) {

        Specification<ForumsViewEntity> masterSpec = null;
        for (FilterCriteria filterCriteria : pageParamDto.getCriteria())
            masterSpec = Specification.where(masterSpec).and(new ForumViewSpec(filterCriteria));

        Page<ForumsViewEntity> list = forumViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());

        return list.map(appMapper::map);
    }

    @Override
    public ForumDto addForum(ForumInput forumInput) {

        Optional<ForumEntity> isExistedForumEntity=forumRepository.findByForumNameIgnoreCaseAndForumTypeDetailForumTypeDetailId(forumInput.getForumName(),forumInput.getForumTypeDetailId());
        if(isExistedForumEntity.isPresent())
        {
            throw new EntityNotFoundException("Уже существует");
        }
        ForumEntity forumEntity = new ForumEntity();
        ForumTypeDetailEntity forumTypeDetail = forumTypeDetailRepository.findById(forumInput.getForumTypeDetailId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_TYPE_DETAIL_NOT_FOUND, forumInput.getForumTypeDetailId())));
        forumEntity.setForumTypeDetail(forumTypeDetail);
        forumEntity.setForumName(forumInput.getForumName());
        forumEntity.setStatus(forumInput.getStatus());
        forumEntity = forumRepository.save(forumEntity);

        return appMapper.map(forumEntity);
    }

    @Override
    public ForumDto updateForum(ForumUpdate forumUpdate) {
        ForumEntity forumEntity = isExistedForumById(forumUpdate.getForumId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_NOT_FOUND, forumUpdate.getForumId())));

        ForumTypeDetailEntity forumTypeDetail = forumTypeDetailRepository.findById(forumUpdate.getForumTypeDetailId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_TYPE_DETAIL_NOT_FOUND, forumUpdate.getForumTypeDetailId())));

        forumEntity.setForumName(forumUpdate.getForumName());
        forumEntity.setForumTypeDetail(forumTypeDetail);

        return appMapper.map(forumEntity);
    }

    @Override
    @Transactional
    public void deleteForumById(Long forumId) {
        ForumEntity forumEntity = isExistedForumById(forumId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_NOT_FOUND, forumId)));

        forumEntity.setStatus(ObjectStatusEnum.DISABLE);

        forumRepository.save(forumEntity);
    }

    @Override
    public Page<ForumTypeDetailDto> getForumTypeDetailPage(String languageCode, PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();

        String forumTypeId = params.get("forumTypeId");

        Specification<ForumTypeDetailEntity> specification = Specification
                .where(languageCodeEqual(languageCode))
                .and(StringUtils.hasText(forumTypeId) ? forumTypeIdEqual(Long.valueOf(forumTypeId)) : null);

        Page<ForumTypeDetailEntity> list = forumTypeDetailRepository.findAll(specification, pageParamDto.getPageRequest());


        return list.map(appMapper::map);
    }

    @Override
    public List<ForumTypeDetailDto> getForumTypeDetailDtoList(String languageCode, PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();

        String forumTypeId = params.get("forumTypeId");

        Specification<ForumTypeDetailEntity> specification = Specification
                .where(languageCodeEqual(languageCode))
                .and(StringUtils.hasText(forumTypeId) ? forumTypeIdEqual(Long.valueOf(forumTypeId)) : null);

        List<ForumTypeDetailEntity> list = forumTypeDetailRepository.findAll(specification);

        return list.stream().map(appMapper::map).toList();
    }


//    @Override
//    public Page<ForumQuestionsCommentsDto> ForumQuestionsCommentsDtoList(PageParamDto pageParamDto) {
//
//        Map<String, String> params = pageParamDto.getParams();
//        String forumQuestionId = params.get("forumQuestionId");
//        Specification<ForumQuestionsCommentsEntity> specification = Specification
//                .where(StringUtils.hasText(forumQuestionId) ? forumQuestionIdEqual(Long.valueOf(forumQuestionId)) : null);
//
//        Page<ForumQuestionsCommentsEntity> list = forumQuestionsCommentsRepository.findAll(specification, pageParamDto.getPageRequest());
//        return list.map(appMapper::map);
//    }

    private ForumTypeDto verifyExistingForumTypeDto(ForumTypeCreateInput forumTypeDto) {
        Optional<ForumTypeEntity> forumTypeEntityOptional = isExistedForumTypeByName(forumTypeDto.getForumTypeName());

        ForumTypeEntity forumTypeEntity;

        if (forumTypeEntityOptional.isPresent()) {
            forumTypeEntity = updatedExistedForumTypeEntity(forumTypeEntityOptional.get(), forumTypeDto);
        } else {
            forumTypeEntity = createNewForumTypeEntity(forumTypeDto);
        }

        return appMapper.map(forumTypeEntity);
    }

    private Optional<ForumTypeEntity> isExistedForumTypeByName(String name) {
        return forumTypeRepository.findByForumTypeNameIgnoreCase(name);
    }

    private Optional<ForumEntity> isExistedForumByForumName(String name) {
        return forumRepository.findByForumNameIgnoreCase(name);
    }

    private Optional<ForumEntity> isExistedForumById(Long id) {
        return forumRepository.findById(id);
    }

    private ForumTypeEntity updatedExistedForumTypeEntity(ForumTypeEntity forumTypeEntity, ForumTypeCreateInput forumTypeDto) {
        forumTypeEntity.setStatus(forumTypeDto.getStatus());
        forumTypeEntity = forumTypeRepository.save(forumTypeEntity);
        return forumTypeEntity;
    }

    private ForumTypeEntity createNewForumTypeEntity(ForumTypeCreateInput forumTypeCreateInput) {
        ForumTypeEntity forumTypeEntity = new ForumTypeEntity();
        forumTypeEntity.setForumTypeName(forumTypeCreateInput.getForumTypeName());
        forumTypeEntity.setStatus(forumTypeCreateInput.getStatus());
        forumTypeEntity = forumTypeRepository.save(forumTypeEntity);

        Long forumTypeId = forumTypeEntity.getForumTypeId();

//        if (!forumTypeCreateInput.getForumTypeDetail().isEmpty()) {
//
//            List<ForumTypeDetailEntity> forumTypeDetailEntities = new ArrayList<>();
//            forumTypeCreateInput.getForumTypeDetail().forEach(r -> {
//                createForumTypeDetailEntity(forumTypeDetailEntities, forumTypeId, r);
//            });
//
//            forumTypeDetailRepository.saveAll(forumTypeDetailEntities);
//        }
        return forumTypeEntity;
    }


    public void updateForumTypeDetailEntity(List<ForumTypeDetailEntity> forumTypeDetailEntities, ForumTypeDetailEntity forumTypeDetailEntity, ForumTypeDetailCreateInput forumTypeDetail) {
        LanguageEntity languageEntity = languageRepository.findByLanguageCode(forumTypeDetail.getLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, forumTypeDetail.getLanguageCode())));
        forumTypeDetailEntity.setLanguage(languageEntity);
        forumTypeDetailEntity.setValue(forumTypeDetail.getValue());
        forumTypeDetailEntities.add(forumTypeDetailEntity);
    }

    public ForumTypeDetailEntity createForumTypeDetailEntity(ForumTypeDetailCreateInput forumTypeDetail) {
        ForumTypeDetailEntity forumTypeDetailEntity = new ForumTypeDetailEntity();
        LanguageEntity languageEntity = languageRepository.findByLanguageCode(forumTypeDetail.getLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, forumTypeDetail.getLanguageCode())));

        forumTypeDetailEntity.setLanguage(languageEntity);
        forumTypeDetailEntity.setValue(forumTypeDetail.getValue());
        forumTypeDetailEntity.setForumTypeId(forumTypeDetail.getForumTypeId());
        return forumTypeDetailEntity;
    }


    public ForumTypeDetailEntity createForumTypeDetailEntity(ForumTypeDetailCreateInput input, ForumTypeDetailEntity forumTypeDetail) {
        LanguageEntity languageEntity = languageRepository.findByLanguageCode(input.getLanguageCode())
                .orElseThrow(() -> new EntityNotFoundException(String.format(LANGUAGE_NOT_FOUND, input.getLanguageCode())));

        forumTypeDetail.setLanguage(languageEntity);
        forumTypeDetail.setValue(input.getValue());
        return forumTypeDetail;
    }

}
