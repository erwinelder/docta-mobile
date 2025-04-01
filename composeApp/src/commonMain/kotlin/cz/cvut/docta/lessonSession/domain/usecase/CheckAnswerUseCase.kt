package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerInput

interface CheckAnswerUseCase {
    suspend fun execute(answerInput: AnswerInput): ResultData<AnswerCheckResult, LessonSessionError>
}