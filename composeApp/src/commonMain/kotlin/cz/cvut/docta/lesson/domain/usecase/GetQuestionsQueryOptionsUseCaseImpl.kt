package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.core.domain.course.CourseContext
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.LessonType
import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions

class GetQuestionsQueryOptionsUseCaseImpl(
    private val lessonRepository: LessonRepository,
    private val courseContext: CourseContext
) : GetQuestionsQueryOptionsUseCase {
    override suspend fun execute(lessonId: Long): LessonQuestionsQueryOptions? {
        val lessonTypeString = lessonRepository.getLessonType(
            courseCode = courseContext.getCourseCode(), lessonId = lessonId
        )
        val lessonType = LessonType.entries.find { it.name == lessonTypeString } ?: return null

        return when (lessonType) {
            LessonType.Default, LessonType.Test -> {
                val defaultLesson = lessonRepository.getDefaultLesson(
                    courseCode = courseContext.getCourseCode(), lessonId = lessonId
                ) ?: return null
                LessonQuestionsQueryOptions.Default(
                    lessonId = lessonId,
                    difficulty = defaultLesson.difficulty,
                    matchAllTags = defaultLesson.matchAllTags,
                    courseCode = courseContext.getCourseCode()
                )
            }
            LessonType.StepByStep -> LessonQuestionsQueryOptions.StepByStep(
                courseCode = courseContext.getCourseCode(), lessonId = lessonId
            )
        }
    }
}