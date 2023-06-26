package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumQuestionsCommentsDto;
import md.gapla.model.dto.view.ForumQuestionCommentsViewDto;
import md.gapla.model.input.forum.ForumQuestionsCommentsInput;
import md.gapla.model.input.forum.ForumQuestionsCommentsUpdate;
import org.springframework.data.domain.Page;

public interface ForumQuestionsCommentService {
    ForumQuestionsCommentsDto addForumQuestionsCommentsDto(ForumQuestionsCommentsInput forumQuestionsCommentsInput);

    ForumQuestionsCommentsDto updateForumQuestionsCommentsDto( ForumQuestionsCommentsUpdate forumQuestionsCommentsUpdate);

    Page<ForumQuestionCommentsViewDto> getForumQuestionsCommentsDtoList(PageParamDto pageParamDto);

    void deleteById(Long id);
}
