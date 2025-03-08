package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.lesson.data.local.model.LessonType
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions

class GetQuestionsQueryOptionsUseCaseImpl(
    private val lessonRepository: LessonRepository,
    private val courseContext: CourseContext
) : GetQuestionsQueryOptionsUseCase {
    override suspend fun execute(lessonId: Long): LessonQuestionsQueryOptions? {
        val lessonType = lessonRepository.getLessonType(
            courseCode = courseContext.getCourseCode(),
            sectionId = courseContext.getSectionId(),
            lessonId = lessonId
        ) ?: return null

        return when (lessonType) {
            LessonType.Default -> {
                val defaultLesson = lessonRepository.getDefaultLesson(
                    courseCode = courseContext.getCourseCode(),
                    sectionId = courseContext.getSectionId(),
                    lessonId = lessonId
                ) ?: return null
                LessonQuestionsQueryOptions.Default(
                    lessonId = lessonId,
                    difficulty = defaultLesson.difficulty.name,
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