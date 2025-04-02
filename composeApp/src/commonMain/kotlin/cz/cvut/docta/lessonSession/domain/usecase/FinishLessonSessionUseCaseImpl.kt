package cz.cvut.docta.lessonSession.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.data.repository.LessonSessionRepository
import cz.cvut.docta.lessonSession.domain.model.CompletedLessonStats
import cz.cvut.docta.lessonSession.mapper.toDomainModel

class FinishLessonSessionUseCaseImpl(
    private val lessonSessionRepository: LessonSessionRepository
) : FinishLessonSessionUseCase {
    override suspend fun execute(): ResultData<CompletedLessonStats, LessonSessionError> {
        return lessonSessionRepository.finishLessonSession().mapData { it.toDomainModel() }
    }
}