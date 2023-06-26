package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumQuestionsCommentsDto;
import md.gapla.model.dto.forum.ForumQuestionsDto;
import md.gapla.model.dto.view.ForumQuestionCommentsViewDto;
import md.gapla.model.input.forum.ForumQuestionsCommentsInput;
import md.gapla.model.input.forum.ForumQuestionsCommentsUpdate;
import md.gapla.model.input.forum.ForumQuestionsInput;
import md.gapla.model.input.forum.ForumQuestionsUpdate;
import md.gapla.service.ForumQuestionsCommentService;
import md.gapla.service.ForumQuestionsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "forum-question-comment")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/forum-question-comment")
@AllArgsConstructor
@CrossOrigin
public class ForumQuestionCommentController {

    private final ForumQuestionsCommentService forumQuestionsCommentService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<ForumQuestionCommentsViewDto>> getForumQuestionsPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(forumQuestionsCommentService.getForumQuestionsCommentsDtoList(pageParamDto));
    }

    @PostMapping
    public ResponseEntity<ForumQuestionsCommentsDto> addForumQuestion(@RequestBody ForumQuestionsCommentsInput forumQuestionsCommentsInput) {
        return ResponseEntity.ok(forumQuestionsCommentService.addForumQuestionsCommentsDto(forumQuestionsCommentsInput));
    }

    @PutMapping
    public ResponseEntity<ForumQuestionsCommentsDto> updateForumQuestion(@RequestBody ForumQuestionsCommentsUpdate forumQuestionsCommentsUpdate) {
        return ResponseEntity.ok(forumQuestionsCommentService.updateForumQuestionsCommentsDto(forumQuestionsCommentsUpdate));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteForumQuestion(@PathVariable("id") Long id) {
        forumQuestionsCommentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
