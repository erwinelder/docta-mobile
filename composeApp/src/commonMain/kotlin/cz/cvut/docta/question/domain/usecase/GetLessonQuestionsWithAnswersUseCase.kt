package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.domain.model.QuestionWithAnswers

interface GetLessonQuestionsWithAnswersUseCase {
    suspend fun execute(lessonId: Long): List<QuestionWithAnswers>
}