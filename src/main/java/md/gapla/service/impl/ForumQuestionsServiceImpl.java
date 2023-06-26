package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumQuestionsDto;
import md.gapla.model.dto.view.ForumQuestionViewDto;
import md.gapla.model.entity.forum.ForumEntity;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.entity.forum.ForumQuestionsImageEntity;
import md.gapla.model.entity.view.ForumQuestionViewEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.forum.ForumQuestionsInput;
import md.gapla.model.input.forum.ForumQuestionsUpdate;
import md.gapla.repository.forum.ForumQuestionsImageRepository;
import md.gapla.repository.forum.ForumQuestionsRepository;
import md.gapla.repository.forum.ForumRepository;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.specification.filters.ForumQuestionViewSpec;
import md.gapla.repository.view.ForumQuestionViewRepository;
import md.gapla.service.ForumQuestionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static md.gapla.util.ErrorMessagesUtils.FORUM_NOT_FOUND;
import static md.gapla.util.ErrorMessagesUtils.FORUM_QUESTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ForumQuestionsServiceImpl implements ForumQuestionsService {

    private final AppMapper appMapper;

    private final ForumRepository forumRepository;
    private final ForumQuestionsRepository forumQuestionsRepository;

    private final ForumQuestionViewRepository forumQuestionViewRepository;

    private final ForumQuestionsImageRepository forumQuestionsImageRepository;

    @Override
    @Transactional
    public ForumQuestionsDto addForumQuestionsDto(ForumQuestionsInput forumQuestionsInput) {
        ForumEntity forumEntity = isExistedForumById(forumQuestionsInput.getForumId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_NOT_FOUND, forumQuestionsInput.getForumId())));

        ForumQuestionsEntity forumQuestions = new ForumQuestionsEntity();
        forumQuestions.setForum(forumEntity);
        forumQuestions.setForumQuestionContent(forumQuestionsInput.getForumQuestionContent());
        forumQuestions.setForumQuestionLabel(forumQuestionsInput.getForumQuestionLabel());

        forumQuestions = forumQuestionsRepository.save(forumQuestions);

        Long forumQuestionId = forumQuestions.getForumQuestionId();

        List<ForumQuestionsImageEntity> images = forumQuestionsInput.getImages().stream().map(appMapper::map).toList();
        images.forEach(r -> {
            r.setForumQuestionsId(forumQuestionId);
        });

        forumQuestionsImageRepository.saveAll(images);

        ForumQuestionsDto forumQuestionsDto = appMapper.map(forumQuestions);
        forumQuestionsDto.setImages(images.stream().map(appMapper::map).toList());
        return forumQuestionsDto;
    }

    @Override
    @Transactional
    public ForumQuestionsDto updateForumQuestionsDto(ForumQuestionsUpdate forumQuestionsUpdate) {
        ForumQuestionsEntity forumQuestions = isExistedForumQuestionsById(forumQuestionsUpdate.getForumQuestionId());
        forumQuestions.setForumQuestionLabel(forumQuestionsUpdate.getForumQuestionLabel());
        forumQuestions.setForumQuestionContent(forumQuestionsUpdate.getForumQuestionContent());

        Long forumQuestionId = forumQuestions.getForumQuestionId();

        List<ForumQuestionsImageEntity> images = forumQuestionsUpdate.getImages().stream().map(appMapper::map).toList();
        images.forEach(r -> {
            r.setForumQuestionsId(forumQuestionId);
        });
        List<ForumQuestionsImageEntity> img = forumQuestionsImageRepository.findByForumQuestionsId(forumQuestionId);

        forumQuestionsImageRepository.deleteAll(img);
        forumQuestionsImageRepository.saveAll(images);
        forumQuestions = forumQuestionsRepository.save(forumQuestions);
        return appMapper.map(forumQuestions);
    }

    @Override
    public Page<ForumQuestionViewDto> getForumQuestionList(PageParamDto pageParamDto) {

        Specification<ForumQuestionViewEntity> masterSpec = null;

        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new ForumQuestionViewSpec(filterCriteria));
        }

        Page<ForumQuestionViewEntity> list = forumQuestionViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());
        list.map(appMapper::map);
        Page<ForumQuestionViewDto> pageDto = list.map(appMapper::map);
        pageDto.forEach(r -> {
            r.setImages(forumQuestionsImageRepository.findByForumQuestionsId(r.getForumQuestionId()).stream().map(appMapper::map).toList());
        });
        return pageDto;
    }

    @Override
    public void deleteById(Long id) {
        ForumQuestionsEntity forumQuestions = isExistedForumQuestionsById(id);
        forumQuestions.setStatus(ObjectStatusEnum.DISABLE);
        forumQuestionsRepository.save(forumQuestions);
    }

    @Override
    public ForumQuestionViewDto getById(Long id) {
        ForumQuestionViewDto forumQuestionViewDto = appMapper.map(forumQuestionViewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Не существует")));
        forumQuestionViewDto.setImages(forumQuestionsImageRepository.findByForumQuestionsId(forumQuestionViewDto.getForumQuestionId()).stream().map(appMapper::map).toList());
        return forumQuestionViewDto;
    }

    private ForumQuestionsEntity isExistedForumQuestionsById(Long id) {
        return forumQuestionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_QUESTION_NOT_FOUND, id)));

    }

    private Optional<ForumEntity> isExistedForumById(Long id) {
        return forumRepository.findById(id);
    }
}
