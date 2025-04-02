package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.lessonSession.domain.model.QuestionWithMaterials
import cz.cvut.docta.lessonSession.domain.model.SessionOptions

interface GetLessonQuestionsWithAnswersUseCase {
    suspend fun execute(sessionOptions: SessionOptions): List<QuestionWithMaterials>
}