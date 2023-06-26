package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumDto;
import md.gapla.model.dto.forum.ForumQuestionsDto;
import md.gapla.model.dto.forum.ForumTypeDto;
import md.gapla.model.dto.view.ForumQuestionViewDto;
import md.gapla.model.input.forum.*;
import md.gapla.service.ForumQuestionsService;
import md.gapla.service.ForumService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "forum-question")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/forum-question")
@AllArgsConstructor
@CrossOrigin
public class ForumQuestionController {

    private final ForumQuestionsService forumQuestionsService;

    @PostMapping(value = "/page")
    public ResponseEntity<Page<ForumQuestionViewDto>> getForumQuestionsPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(forumQuestionsService.getForumQuestionList(pageParamDto));
    }

    @PostMapping
    public ResponseEntity<ForumQuestionsDto> addForumQuestion(@RequestBody ForumQuestionsInput forumQuestionsInput) {
        return ResponseEntity.ok(forumQuestionsService.addForumQuestionsDto(forumQuestionsInput));
    }

    @PutMapping
    public ResponseEntity<ForumQuestionsDto> updateForumQuestion(@RequestBody ForumQuestionsUpdate forumQuestionsUpdate) {
        return ResponseEntity.ok(forumQuestionsService.updateForumQuestionsDto(forumQuestionsUpdate));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteForumQuestion(@PathVariable("id") Long id) {
        forumQuestionsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ForumQuestionViewDto> getForumQuestion(@PathVariable("id") Long id) {
        return ResponseEntity.ok( forumQuestionsService.getById(id));
    }
}
