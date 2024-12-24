package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.mapper.toDomainModels

class GetSectionLessonsUseCaseImpl(
    private val lessonRepository: LessonRepository
) : GetSectionLessonsUseCase {
    override suspend fun execute(sectionId: Long): List<Lesson> {
        return lessonRepository.getSectionLessons(sectionId).toDomainModels()
    }
}