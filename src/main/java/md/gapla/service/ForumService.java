package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.*;
import md.gapla.model.dto.view.ForumTypeViewDto;
import md.gapla.model.dto.view.ForumsViewDto;
import md.gapla.model.input.forum.ForumInput;
import md.gapla.model.input.forum.ForumTypeCreateInput;
import md.gapla.model.input.forum.ForumTypeDetailCreateInput;
import md.gapla.model.input.forum.ForumUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ForumService {

    ForumTypeDto addForumType(ForumTypeCreateInput forumTypeDto);

    ForumTypeDto updateForumType(ForumTypeCreateInput forumTypeDto);

    void saveForumTypeDetail(ForumTypeDetailCreateInput input);
    void updateForumTypeDetail(ForumTypeDetailCreateInput input);


    void deleteForumTypeById(Long forumTypeId);

    Page<ForumTypeDto> getForumTypePage(PageParamDto pageParamDto);

    Page<ForumTypeViewDto> getForumTypeViewPage(PageParamDto pageParamDto);
    List<ForumTypeViewDto> getForumTypeViewList(PageParamDto pageParamDto);

    Page<ForumTypeDetailDto> getForumTypeDetailPage(PageParamDto pageParamDto);

    Page<ForumsViewDto> getForumDtoPage(PageParamDto pageParamDto);

    ForumDto addForum(ForumInput forumInput);

    ForumDto updateForum(ForumUpdate forumUpdate);

    void deleteForumById(Long forumId);

    Page<ForumTypeDetailDto> getForumTypeDetailPage(String languageCode, PageParamDto pageParamDto);

    List<ForumTypeDetailDto> getForumTypeDetailDtoList(String languageCode, PageParamDto pageParamDto);


}
