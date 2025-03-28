package cz.cvut.docta.lessonSession.data.repository

import cz.cvut.docta.lessonSession.data.model.QuestionWithCorrectAnswersDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto

interface LessonSessionRepository {

    suspend fun getDefaultQuestionsWithCorrectAnswers(
        sessionOptions: SessionOptionsDto
    ): List<QuestionWithCorrectAnswersDto>

}