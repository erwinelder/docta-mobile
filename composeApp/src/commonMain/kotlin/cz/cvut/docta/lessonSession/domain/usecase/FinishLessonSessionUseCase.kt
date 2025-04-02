package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.domain.model.CompletedLessonStats

interface FinishLessonSessionUseCase {
    suspend fun execute(): ResultData<CompletedLessonStats, LessonSessionError>
}