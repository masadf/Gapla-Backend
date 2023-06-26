package md.gapla.service;

import md.gapla.model.dto.courseexam.ExamDto;

public interface ExamCourseService {

    ExamDto getRandomExamByCourseId(Long courseId);
}
