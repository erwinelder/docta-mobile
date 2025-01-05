package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.LessonType
import cz.cvut.docta.section.data.repository.SectionRepository

class GetLessonQuestionsQueryOptionsUseCaseImpl(
    private val sectionRepository: SectionRepository,
    private val lessonRepository: LessonRepository
) : GetLessonQuestionsQueryOptionsUseCase {
    override suspend fun execute(lessonId: Long): LessonQuestionsQueryOptions? {
        val lessonTypeString = lessonRepository.getLessonType(lessonId = lessonId)
        val lessonType = LessonType.entries.find { it.name == lessonTypeString } ?: return null

        return when (lessonType) {
            LessonType.Default, LessonType.Test -> {
                val defaultLesson = lessonRepository.getDefaultLesson(lessonId = lessonId)
                    ?: return null
                val section = sectionRepository.getSection(defaultLesson.sectionId)
                    ?: return null
                LessonQuestionsQueryOptions.Default(
                    lessonId = lessonId,
                    difficulty = defaultLesson.difficulty,
                    matchAllTags = defaultLesson.matchAllTags,
                    courseCode = section.courseCode
                )
            }
            LessonType.StepByStep -> LessonQuestionsQueryOptions.StepByStep(lessonId = lessonId)
        }
    }
}