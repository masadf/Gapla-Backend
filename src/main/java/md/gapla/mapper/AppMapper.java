package md.gapla.mapper;

import md.gapla.model.dto.LanguageDto;
import md.gapla.model.dto.account.*;
import md.gapla.model.dto.common.*;
import md.gapla.model.dto.course.CourseDetailsDto;
import md.gapla.model.dto.course.CourseDto;
import md.gapla.model.dto.course.CourseShortDto;
import md.gapla.model.dto.courseexam.ExamDto;
import md.gapla.model.dto.courseexam.ExamQuestionTaskTextDto;
import md.gapla.model.dto.courseexam.ExamTaskDto;
import md.gapla.model.dto.forum.*;
import md.gapla.model.dto.lessons.*;
import md.gapla.model.dto.levellanguage.LevelLanguageDetailDto;
import md.gapla.model.dto.levellanguage.LevelLanguageDto;
import md.gapla.model.dto.meeting.MeetingDto;
import md.gapla.model.dto.meeting.OnlineLessonDto;
import md.gapla.model.dto.meeting.ParticipantDto;
import md.gapla.model.dto.test.*;
import md.gapla.model.dto.view.*;
import md.gapla.model.entity.LanguageEntity;
import md.gapla.model.entity.account.*;
import md.gapla.model.entity.common.*;
import md.gapla.model.entity.course.CourseDetailsEntity;
import md.gapla.model.entity.course.CourseEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.model.entity.courseexam.ExamQuestionTaskTextEntity;
import md.gapla.model.entity.courseexam.ExamTaskEntity;
import md.gapla.model.entity.forum.*;
import md.gapla.model.entity.lessons.LessonEntity;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeEntity;
import md.gapla.model.entity.lessons.LessonMaterialsTypeLanguageEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageDetailEntity;
import md.gapla.model.entity.levellanguage.LevelLanguageEntity;
import md.gapla.model.entity.meeting.MeetingEntity;
import md.gapla.model.entity.test.*;
import md.gapla.model.entity.view.*;
import md.gapla.model.enums.CommonInfoTypeEnum;
import md.gapla.model.input.CommonInfoLanguageDetailInput;
import md.gapla.model.input.CommonInfoLanguageInput;
import md.gapla.model.input.test.TestInput;
import md.kobalt.security.user.JwtUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppMapper {

    default CommonInfoTypeEnum getCommonInfoTypeEnum(String commonInfoType) {
        if (commonInfoType == null) return null;
        for (CommonInfoTypeEnum commonInfoTypeEnum : CommonInfoTypeEnum.values()) {
            if (commonInfoTypeEnum.getValue().equals(commonInfoType)) {
                return commonInfoTypeEnum;
            }
        }
        return null;
    }

    CourseDetailsDto map(CourseDetailsEntity obj);

    ExamTaskDto map(ExamTaskEntity obj);

    MeetingTypeViewDto map(MeetingTypeViewEntity obj);

    MeetingViewDto map(MeetingViewEntity obj);
    OnlineLessonDto mapToOnlineLessonDto(MeetingEntity obj);

    MeetingParticipantViewDto map(MeetingParticipantViewEntity obj);

    ExamQuestionTaskTextDto map(ExamQuestionTaskTextEntity obj);

    @Mapping(target = "email", ignore = true)
    AccountCheckLevelDto map(AccountCheckLevelEntity obj);

    @Mapping(target = "email", ignore = true)
    AccountExamCheckLevelDto mapToExam(AccountCheckLevelEntity obj);

    ForumQuestionsCommentsImageDto map(ForumQuestionsCommentsImageEntity obj);

    ForumQuestionsCommentsImageEntity map(ForumQuestionsCommentsImageDto obj);

    TestQuestionBriefDto mapToBrief(TestQuestionEntity obj);

    LessonBriefDto mapToBrief(LessonEntity obj);

    ForumQuestionsImageDto map(ForumQuestionsImageEntity obj);

    ForumQuestionsImageEntity map(ForumQuestionsImageDto obj);

    MeetingDto map(MeetingEntity obj);

    AccountCourseLessonProgressDto map(AccountCourseLessonProgressEntity obj);

    AccountCourseLessonProgressEntity map(AccountCourseLessonProgressDto obj);

    AccountCourseProgressDto map(AccountCourseProgressEntity obj);

    AccountCourseProgressEntity map(AccountCourseProgressDto obj);

    AccountDto map(AccountEntity obj);

    ParticipantDto mapToParticipant(AccountEntity obj);

    JwtUserDetails mapToDetails(AccountEntity obj);

    AccountEntity map(AccountDto obj);

    @Mapping(target = "reading", ignore = true)
    @Mapping(target = "listening", ignore = true)
    @Mapping(target = "tasksQuantity", expression = "java(obj.getTasks().size())")
    ExamDto map(ExamEntity obj);
    
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    ExamEntity map(ExamDto obj);
    
    AccountRoleDto map(AccountRoleEntity obj);

    AccountRoleEntity map(AccountRoleDto obj);

    @Mapping(target = "commonInfoType", ignore = true)
    CommonInfoEntity map(CommonInfoDto obj);

    @Mapping(target = "commonInfoType", expression = "java(getCommonInfoTypeEnum(obj.getCommonInfoType().getCommonInfoType()))")
    CommonInfoDto map(CommonInfoEntity obj);

    CommonInfoTypeEntity map(CommonInfoTypeDto obj);

    @Mapping(target = "commonInfoType", expression = "java(getCommonInfoTypeEnum(obj.getCommonInfoType()))")
    CommonInfoTypeDto map(CommonInfoTypeEntity obj);

    CommonInfoTypeLanguageEntity map(CommonInfoTypeLanguageDto obj);

    CommonInfoTypeLanguageDto map(CommonInfoTypeLanguageEntity obj);

    CommonInfoImageDto map(CommonInfoImageEntity obj);

    CommonInfoImageEntity map(CommonInfoImageDto obj);

    CommonInfoLanguageDetailDto map(CommonInfoLanguageDetailEntity obj);

    CommonInfoLanguageDetailEntity map(CommonInfoLanguageDetailDto obj);

    CommonInfoLanguageDetailEntity map(CommonInfoLanguageDetailInput obj);

    @Mapping(target = "commonInfoId", source = "commonInfo.commonInfoId")
    @Mapping(target = "commonInfoCode", source = "commonInfo.commonInfoCode")
//    @Mapping(target = "commonInfoType", expression = "java(getCommonInfoTypeEnum(obj.getCommonInfo().getCommonInfoType().getCommonInfoType()))")
    CommonInfoLanguageDto map(CommonInfoLanguageEntity obj);


    CommonInfoLanguageEntity map(CommonInfoLanguageDto obj);

    CommonInfoLanguageEntity map(CommonInfoLanguageInput obj);

    CommonQuestionDto map(CommonQuestionEntity obj);

    CommonQuestionEntity map(CommonQuestionDto obj);

    @Mapping(target = "tests", ignore = true)
    CourseDto map(CourseEntity obj);

    CourseShortDto mapToShort(CourseEntity obj);

    @Mapping(target = "tests", ignore = true)
    CourseEntity map(CourseDto obj);

    ForumDto map(ForumEntity obj);

    ForumEntity map(ForumDto obj);

    ForumQuestionsCommentsEntity map(ForumQuestionsCommentsDto obj);

    ForumQuestionsCommentsDto map(ForumQuestionsCommentsEntity obj);

    ForumQuestionsDto map(ForumQuestionsEntity obj);

    ForumQuestionsEntity map(ForumQuestionsDto obj);

    ForumTypeDetailDto map(ForumTypeDetailEntity obj);

    ForumTypeDetailEntity map(ForumTypeDetailDto obj);

    ForumTypeDto map(ForumTypeEntity obj);

    ForumTypeEntity map(ForumTypeDto obj);

    LessonDto map(LessonEntity obj);

    LessonEntity map(LessonDto obj);

    @Mapping(target = "lessonId", source = "lesson.lessonId")
    LessonMaterialsDto map(LessonMaterialsEntity obj);

    LessonMaterialsEntity map(LessonMaterialsDto obj);

    LessonMaterialsTypeDto map(LessonMaterialsTypeEntity obj);

    LessonMaterialsTypeEntity map(LessonMaterialsTypeDto obj);

    @Mapping(target = "languageCode", source = "language.languageCode")
    @Mapping(target = "lessonMaterialsTypeId", source = "lessonMaterialsType.lessonMaterialsTypeId")
    LessonMaterialsTypeLanguageDto map(LessonMaterialsTypeLanguageEntity obj);

    LessonMaterialsTypeLanguageEntity map(LessonMaterialsTypeLanguageDto obj);

    LevelLanguageDetailDto map(LevelLanguageDetailEntity obj);

    LevelLanguageDetailEntity map(LevelLanguageDetailDto obj);

    LevelLanguageDto map(LevelLanguageEntity obj);

    LevelLanguageEntity map(LevelLanguageDto obj);

    TestAnswerDto map(TestAnswerEntity obj);

    TestAnswerEntity map(TestAnswerDto obj);

    @Mapping(target = "testTimeTypeId", source = "testTimeType.testTimeTypeId")
    @Mapping(target = "questionsQuantity", expression = "java(obj.getQuestions().size())")
    TestDto map(TestEntity obj);

    TestEntity map(TestDto obj);

    @Mapping(target = "testText.value", source = "testText")
    @Mapping(target = "questions", ignore = true)
    TestEntity map(TestInput obj);

    @Mapping(target = "testQuestionTypeId", source = "testQuestionTypeLanguage.testQuestionType.testQuestionTypeId")
    @Mapping(target = "testQuestionTypeValue", source = "testQuestionTypeLanguage.value")
    TestQuestionDto map(TestQuestionEntity obj);

    TestQuestionEntity map(TestQuestionDto obj);

    TestQuestionTypeDetailDto map(TestQuestionTypeDetailEntity obj);

    TestQuestionTypeDetailEntity map(TestQuestionTypeDetailDto obj);

    TestQuestionTypeDto map(TestQuestionTypeEntity obj);

    TestQuestionTypeEntity map(TestQuestionTypeDto obj);

    @Mapping(target = "languageCode", source = "language.languageCode")
    @Mapping(target = "testQuestionTypeId", source = "testQuestionType.testQuestionTypeId")
    TestQuestionTypeLanguageDto map(TestQuestionTypeLanguageEntity obj);

    TestQuestionTypeLanguageEntity map(TestQuestionTypeLanguageDto obj);

    TestTextDto map(TestTextEntity obj);

    TestTextEntity map(TestTextDto obj);

    LevelLanguageViewDto map(LevelLanguageViewEntity obj);

    CourseViewDto map(CourseViewEntity obj);

    TimeTypeViewDto map(TimeTypeViewEntity obj);

    AccountViewDto map(AccountViewEntity obj);

    TestTimeTypeDetailDto map(TestTimeTypeDetailEntity obj);

    TestTimeTypeDetailEntity map(TestTimeTypeDetailDto obj);

    TestTimeTypeDto map(TestTimeTypeEntity obj);

    TestTimeTypeEntity map(TestTimeTypeDto obj);

    @Mapping(target = "testTime", source = "testTimeType.testTime")
    TestTimeTypeLanguageDto map(TestTimeTypeLanguageEntity obj);

    TestTimeTypeLanguageEntity map(TestTimeTypeLanguageDto obj);

    LanguageDto map(LanguageEntity obj);

    LanguageEntity map(LanguageDto obj);

    ForumTypeViewDto map(ForumTypeViewEntity obj);

    ForumsViewDto map(ForumsViewEntity obj);

    ForumQuestionViewDto map(ForumQuestionViewEntity obj);

    ForumQuestionCommentsViewDto map(ForumQuestionCommentsViewEntity obj);
}
