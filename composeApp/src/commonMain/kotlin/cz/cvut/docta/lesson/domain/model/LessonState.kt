package cz.cvut.docta.lesson.domain.model

import cz.cvut.docta.lessonSession.domain.model.QuestionWithAnswerInput

sealed class LessonState {

    data class LessonQuestion(
        val question: QuestionWithAnswerInput
    ) : LessonState()

    data class Results(
        val lessonResults: LessonResults
    ) : LessonState()

}
