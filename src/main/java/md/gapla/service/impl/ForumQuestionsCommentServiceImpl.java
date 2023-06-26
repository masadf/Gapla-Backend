package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.exception.EntityNotFoundException;
import md.gapla.mapper.AppMapper;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumQuestionsCommentsDto;
import md.gapla.model.dto.view.ForumQuestionCommentsViewDto;
import md.gapla.model.entity.forum.ForumQuestionsCommentsEntity;
import md.gapla.model.entity.forum.ForumQuestionsCommentsImageEntity;
import md.gapla.model.entity.forum.ForumQuestionsEntity;
import md.gapla.model.entity.view.ForumQuestionCommentsViewEntity;
import md.gapla.model.enums.ObjectStatusEnum;
import md.gapla.model.input.forum.ForumQuestionsCommentsInput;
import md.gapla.model.input.forum.ForumQuestionsCommentsUpdate;
import md.gapla.repository.forum.ForumQuestionsCommentsImageRepository;
import md.gapla.repository.forum.ForumQuestionsCommentsRepository;
import md.gapla.repository.forum.ForumQuestionsRepository;
import md.gapla.repository.specification.filters.FilterCriteria;
import md.gapla.repository.specification.filters.ForumQuestionCommentsViewSpec;
import md.gapla.repository.view.ForumQuestionCommentsViewRepository;
import md.gapla.service.ForumQuestionsCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static md.gapla.util.ErrorMessagesUtils.FORUM_QUESTION_COMMENTS_NOT_FOUND;
import static md.gapla.util.ErrorMessagesUtils.FORUM_QUESTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ForumQuestionsCommentServiceImpl implements ForumQuestionsCommentService {

    private final AppMapper appMapper;
    private final ForumQuestionsRepository forumQuestionsRepository;
    private final ForumQuestionsCommentsRepository forumQuestionsCommentsRepository;

    private final ForumQuestionCommentsViewRepository forumQuestionCommentsViewRepository;

    private final ForumQuestionsCommentsImageRepository forumQuestionsCommentsImageRepository;

    @Override
    @Transactional
    public ForumQuestionsCommentsDto addForumQuestionsCommentsDto(ForumQuestionsCommentsInput forumQuestionsCommentsInput) {
        ForumQuestionsEntity forumQuestions = isExistedForumQuestionsById(forumQuestionsCommentsInput.getForumQuestionId());
        ForumQuestionsCommentsEntity forumQuestionsCommentsEntity = new ForumQuestionsCommentsEntity();

        if (forumQuestionsCommentsInput.getFromReplyId() != null)
            forumQuestionsCommentsEntity.setReplyFromCommentId(forumQuestionsCommentsRepository.findById(forumQuestionsCommentsInput.getFromReplyId()).orElseThrow(() -> new EntityNotFoundException("Не найдено")).getForumQuestionsCommentId());

        forumQuestionsCommentsEntity.setForumQuestion(forumQuestions);
        forumQuestionsCommentsEntity.setComment(forumQuestionsCommentsInput.getComment());
        forumQuestionsCommentsEntity.setStatus(ObjectStatusEnum.ENABLE);

        forumQuestionsCommentsEntity = forumQuestionsCommentsRepository.save(forumQuestionsCommentsEntity);

        List<ForumQuestionsCommentsImageEntity> forumQuestionsCommentsImageEntities = forumQuestionsCommentsInput.getImages().stream().map(appMapper::map).toList();
        List<ForumQuestionsCommentsImageEntity> forumQuestionsCommentsImageEntities1 = forumQuestionsCommentsImageRepository.findByForumQuestionsCommentId(forumQuestionsCommentsEntity.getForumQuestionsCommentId());
        Long forumQuestionsCommentsId = forumQuestionsCommentsEntity.getForumQuestionsCommentId();
        forumQuestionsCommentsImageEntities.forEach(r -> {
            r.setForumQuestionsCommentId(forumQuestionsCommentsId);
        });
        forumQuestionsCommentsImageRepository.deleteAll(forumQuestionsCommentsImageEntities1);
        forumQuestionsCommentsImageRepository.saveAll(forumQuestionsCommentsImageEntities);

        ForumQuestionsCommentsDto dto = appMapper.map(forumQuestionsCommentsEntity);
        dto.setImages(forumQuestionsCommentsImageEntities.stream().map(appMapper::map).toList());
        return dto;
    }

    @Override
    public ForumQuestionsCommentsDto updateForumQuestionsCommentsDto(ForumQuestionsCommentsUpdate forumQuestionsCommentsUpdate) {
        ForumQuestionsCommentsEntity forumQuestionsCommentsEntity = isExistedForumQuestionsCommentsById(forumQuestionsCommentsUpdate.getForumQuestionsCommentId());
        forumQuestionsCommentsEntity.setComment(forumQuestionsCommentsUpdate.getComment());

        List<ForumQuestionsCommentsImageEntity> forumQuestionsCommentsImageEntities = forumQuestionsCommentsUpdate.getImages().stream().map(appMapper::map).toList();
        List<ForumQuestionsCommentsImageEntity> forumQuestionsCommentsImageEntities1 = forumQuestionsCommentsImageRepository.findByForumQuestionsCommentId(forumQuestionsCommentsEntity.getForumQuestionsCommentId());
        Long forumQuestionsCommentsId = forumQuestionsCommentsEntity.getForumQuestionsCommentId();
        forumQuestionsCommentsImageEntities.forEach(r -> {
            r.setForumQuestionsCommentId(forumQuestionsCommentsId);
        });
        forumQuestionsCommentsImageRepository.deleteAll(forumQuestionsCommentsImageEntities1);
        forumQuestionsCommentsImageRepository.saveAll(forumQuestionsCommentsImageEntities);

        ForumQuestionsCommentsDto dto = appMapper.map(forumQuestionsCommentsEntity);
        dto.setImages(forumQuestionsCommentsImageEntities.stream().map(appMapper::map).toList());
        return dto;
    }

    @Override
    public Page<ForumQuestionCommentsViewDto> getForumQuestionsCommentsDtoList(PageParamDto pageParamDto) {

        Specification<ForumQuestionCommentsViewEntity> masterSpec = null;

        for (FilterCriteria filterCriteria : pageParamDto.getCriteria()) {
            masterSpec = Specification.where(masterSpec).and(new ForumQuestionCommentsViewSpec(filterCriteria));
        }

        Page<ForumQuestionCommentsViewEntity> list = forumQuestionCommentsViewRepository.findAll(masterSpec, pageParamDto.getPageRequest());

        Page<ForumQuestionCommentsViewDto> listd = list.map(appMapper::map);
        listd.forEach(r -> {
            r.setImages(forumQuestionsCommentsImageRepository.findByForumQuestionsCommentId(r.getForumQuestionsCommentId()).stream().map(appMapper::map).toList());
        });
        return listd;
    }

    @Override
    public void deleteById(Long id) {
        ForumQuestionsCommentsEntity forumQuestionsCommentsEntity = isExistedForumQuestionsCommentsById(id);
        forumQuestionsCommentsEntity.setStatus(ObjectStatusEnum.DISABLE);
        forumQuestionsCommentsRepository.save(forumQuestionsCommentsEntity);
    }

    private ForumQuestionsEntity isExistedForumQuestionsById(Long id) {
        return forumQuestionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_QUESTION_NOT_FOUND, id)));

    }

    private ForumQuestionsCommentsEntity isExistedForumQuestionsCommentsById(Long id) {
        return forumQuestionsCommentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(FORUM_QUESTION_COMMENTS_NOT_FOUND, id)));

    }
}
