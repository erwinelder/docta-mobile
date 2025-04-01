package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.data.repository.LessonSessionRepository
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.mapper.toDomainModel
import cz.cvut.docta.lessonSession.mapper.toDto

class CheckAnswerUseCaseImpl(
    private val lessonSessionRepository: LessonSessionRepository
) : CheckAnswerUseCase {
    override suspend fun execute(
        answerInput: AnswerInput
    ): ResultData<AnswerCheckResult, LessonSessionError> {
        return lessonSessionRepository
            .checkAnswer(answerInput = answerInput.toDto())
            .mapData { it.toDomainModel() }
    }
}