package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.lessons.LessonDto;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.input.lesson.LessonInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LessonService {

    Page<LessonDto> getLessonDtoPage(PageParamDto pageParamDto);
    List<LessonDto> getLessonList(PageParamDto pageParamDto);

    LessonDto addLessonDto(LessonInput input);
    LessonDto updateLessonDto(LessonInput input);
    void  deleteLessonById(Long lessonId);
    LessonDto  getLessonById(Long lessonId);
    List<LessonDto> getLessonByCourseId(Long courseId);
}
