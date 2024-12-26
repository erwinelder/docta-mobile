package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.mapper.toLessonList

class GetSectionLessonsWithStatisticsUseCaseImpl(
    private val lessonRepository: LessonRepository
) : GetSectionLessonsWithStatisticsUseCase {
    override suspend fun execute(sectionId: Long): List<Lesson> {
        return lessonRepository.getSectionLessonsWithStatistics(sectionId).toLessonList()
    }
}