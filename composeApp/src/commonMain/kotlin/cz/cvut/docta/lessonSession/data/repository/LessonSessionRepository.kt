package cz.cvut.docta.lessonSession.data.repository

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.data.model.answer.AnswerCheckResultDto
import cz.cvut.docta.lessonSession.data.model.answer.AnswerInputDto
import cz.cvut.docta.lessonSession.data.model.QuestionWrapperDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto

interface LessonSessionRepository {

    suspend fun getDefaultQuestionsWithCorrectAnswers(
        sessionOptions: SessionOptionsDto
    ): List<QuestionWrapperDto>

    suspend fun checkAnswer(
        answerInput: AnswerInputDto
    ): ResultData<AnswerCheckResultDto, LessonSessionError>

}