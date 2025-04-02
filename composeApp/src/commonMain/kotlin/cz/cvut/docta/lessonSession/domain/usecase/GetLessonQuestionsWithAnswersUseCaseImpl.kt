package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.lessonSession.data.repository.LessonSessionRepository
import cz.cvut.docta.lessonSession.domain.model.question.QuestionWithMaterials
import cz.cvut.docta.lessonSession.domain.model.SessionOptions
import cz.cvut.docta.lessonSession.mapper.toDomainModel
import cz.cvut.docta.lessonSession.mapper.toDto

class GetLessonQuestionsWithAnswersUseCaseImpl(
    private val courseContext: CourseContext,
    private val lessonSessionRepository: LessonSessionRepository
) : GetLessonQuestionsWithAnswersUseCase {
    override suspend fun execute(sessionOptions: SessionOptions): List<QuestionWithMaterials> {
        val options = sessionOptions.toDto(courseCode = courseContext.getCourseCode())
        return lessonSessionRepository
            .getDefaultQuestionsWithCorrectAnswers(sessionOptions = options)
            .map { it.toDomainModel() }
    }
}