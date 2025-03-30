package cz.cvut.docta.lessonSession.data.repository

import cz.cvut.docta.lessonSession.data.model.QuestionWrapperDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto

interface LessonSessionRepository {

    suspend fun getDefaultQuestionsWithCorrectAnswers(
        sessionOptions: SessionOptionsDto
    ): List<QuestionWrapperDto>

}