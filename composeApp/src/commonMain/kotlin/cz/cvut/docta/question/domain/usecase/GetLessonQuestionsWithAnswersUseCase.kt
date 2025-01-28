package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers

interface GetLessonQuestionsWithAnswersUseCase {
    suspend fun execute(lessonId: Long): List<QuestionWithCorrectAnswers>
}