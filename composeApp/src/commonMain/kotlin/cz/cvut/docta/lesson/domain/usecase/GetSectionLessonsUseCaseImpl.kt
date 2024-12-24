package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.domain.model.Lesson

class GetSectionLessonsUseCaseImpl(
//    private val lessonRepository: LessonRepository
) : GetSectionLessonsUseCase {
    override suspend fun execute(sectionId: Long): List<Lesson> {
//        return lessonRepository.getSectionLessons(sectionId = sectionId)
        return emptyList() // TODO-USECASE
    }
}