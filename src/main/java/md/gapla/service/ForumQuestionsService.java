package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumQuestionsDto;
import md.gapla.model.dto.view.ForumQuestionViewDto;
import md.gapla.model.input.forum.ForumQuestionsInput;
import md.gapla.model.input.forum.ForumQuestionsUpdate;
import org.springframework.data.domain.Page;

public interface ForumQuestionsService {
    ForumQuestionsDto addForumQuestionsDto(ForumQuestionsInput forumQuestionsInput);

    ForumQuestionsDto updateForumQuestionsDto(ForumQuestionsUpdate forumQuestionsUpdate);

    Page<ForumQuestionViewDto> getForumQuestionList(PageParamDto pageParamDto);

    void deleteById(Long id);
    ForumQuestionViewDto getById(Long id);
}
