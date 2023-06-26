package md.gapla.service;

import md.gapla.model.dto.PageParamDto;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.dto.view.CourseViewDto;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.course.CourseLanguageEntity;
import md.gapla.model.input.CourseInput;
import md.gapla.repository.specification.filters.FilterCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    Page<CourseViewDto> getCoursePage(PageParamDto pageParamDto);


    CourseDto getCourse(Long courseId);
    CourseEntity getCourseEntity(Long courseId);

    CourseDto addCourse(CourseInput input);

    CourseDto updateCourse(CourseInput input);

    List<CourseEntity> findAll();

    List<CourseLanguageEntity> findAll(String languageCode);

    void deleteCourseById(Long courseId);
}
