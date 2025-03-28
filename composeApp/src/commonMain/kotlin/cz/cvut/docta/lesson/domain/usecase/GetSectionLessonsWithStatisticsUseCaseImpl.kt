package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.mapper.toDomainLessons

class GetSectionLessonsWithStatisticsUseCaseImpl(
    private val lessonRepository: LessonRepository,
    private val courseContext: CourseContext
) : GetSectionLessonsWithStatisticsUseCase {
    override suspend fun execute(sectionId: Int): List<LessonWithProgress> {
        return lessonRepository
            .getLessonsWithProgress(
                courseCode = courseContext.getCourseCode(), sectionId = sectionId
            )
            .sortedBy { it.orderNum }
            .toDomainLessons()
    }
}