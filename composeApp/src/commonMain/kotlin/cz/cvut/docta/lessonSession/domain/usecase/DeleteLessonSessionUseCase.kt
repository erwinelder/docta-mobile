package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

interface DeleteLessonSessionUseCase {
    suspend fun execute(): ResultData<Unit, LessonSessionError>
}