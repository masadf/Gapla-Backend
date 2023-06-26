package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.repository.exam.*;
import md.gapla.service.CourseService;
import md.gapla.service.ExamCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ExamCourseServiceImpl implements ExamCourseService {

    private final ExamRepository examRepository;
    private final ExamTaskRepository examTaskRepository;

    private final ExamQuestionRepository examQuestionRepository;
    private final ExamQuestionAnswerRepository examQuestionAnswerRepository;

    private final ExamTextRepository examTextRepository;



    @Override
    public ExamDto getRandomExamByCourseId(Long courseId) {

//        List<ExamEntity>  examEntities=examRepository.findByCoursesCourseId(courseId);
        return null;
    }


    private Integer getRandoNumberTest(Integer max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }
}
