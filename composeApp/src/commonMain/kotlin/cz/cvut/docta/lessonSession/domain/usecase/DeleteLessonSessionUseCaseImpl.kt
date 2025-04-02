package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.data.repository.LessonSessionRepository

class DeleteLessonSessionUseCaseImpl(
    private val lessonSessionRepository: LessonSessionRepository
) : DeleteLessonSessionUseCase {
    override suspend fun execute(): ResultData<Unit, LessonSessionError> {
        return lessonSessionRepository.deleteLessonSession()
    }
}