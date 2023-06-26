package md.gapla.controller;

import lombok.AllArgsConstructor;
import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.forum.ForumDto;
import md.gapla.model.dto.forum.ForumQuestionsDto;
import md.gapla.model.dto.forum.ForumTypeDto;
import md.gapla.model.dto.view.ForumTypeViewDto;
import md.gapla.model.dto.view.ForumsViewDto;
import md.gapla.model.input.forum.ForumInput;
import md.gapla.model.input.forum.ForumTypeCreateInput;
import md.gapla.model.input.forum.ForumTypeDetailCreateInput;
import md.gapla.model.input.forum.ForumUpdate;
import md.gapla.service.ForumQuestionsService;
import md.gapla.service.ForumService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "forum")
//@OpenAPIDefinition(
//        info = @Info(title = "Users API", version = "0.0.1-SNAPSHOT"))
@RequestMapping("api/v1/forum")
@AllArgsConstructor
@CrossOrigin
public class ForumController {

    private final ForumService forumService;


    @PostMapping(value = "/type-page")
    public ResponseEntity<Page<ForumTypeDto>> getForumTypePage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(forumService.getForumTypePage(pageParamDto));
    }

    @PostMapping(value = "/type-detail-page")
    public ResponseEntity<Page<ForumTypeViewDto>> getForumTypeViewPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(forumService.getForumTypeViewPage(pageParamDto));
    }
    @PostMapping(value = "/type-detail-list")
    public ResponseEntity<List<ForumTypeViewDto>> getForumTypeViewList(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(forumService.getForumTypeViewList(pageParamDto));
    }

    @PostMapping(value = "/type-detail-page/save")
    public ResponseEntity<Void> saveForumTypeDetail(@RequestBody ForumTypeDetailCreateInput forumTypeDetailCreateInput) {
      forumService.saveForumTypeDetail(forumTypeDetailCreateInput);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/type-detail-page/save")
    public ResponseEntity<Void> updateForumTypeDetail(@RequestBody ForumTypeDetailCreateInput forumTypeDetailCreateInput) {
        forumService.updateForumTypeDetail(forumTypeDetailCreateInput);

        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "/type-page/save")
    public ResponseEntity<ForumTypeDto> addForumType(@RequestBody ForumTypeCreateInput forumTypeDto) {
        return ResponseEntity.ok(forumService.addForumType(forumTypeDto));
    }

    @PutMapping(value = "/type-page/save")
    public ResponseEntity<ForumTypeDto> updateForumType(@RequestBody ForumTypeCreateInput forumTypeDto) {
        return ResponseEntity.ok(forumService.updateForumType(forumTypeDto));
    }

    @DeleteMapping(value = "/type/{id}")
    public ResponseEntity<Void> deleteForumType(@PathVariable("id") Long id) {
        forumService.deleteForumTypeById(id);
        return   ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Page<ForumsViewDto>> getForumPage(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(forumService.getForumDtoPage(pageParamDto));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ForumDto> addForum(@RequestBody ForumInput forumInput) {
        return ResponseEntity.ok(forumService.addForum(forumInput));
    }

    @PutMapping(value = "/save")
    public ResponseEntity<ForumDto> updateForum(@RequestBody ForumUpdate forumUpdate) {
        return ResponseEntity.ok(forumService.updateForum(forumUpdate));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable("id") Long id) {
        forumService.deleteForumById(id);
       return   ResponseEntity.ok().build();
    }
}
