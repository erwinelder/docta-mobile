package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.core.domain.course.CourseContext
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.mapper.toDomainLessons

class GetSectionLessonsWithStatisticsUseCaseImpl(
    private val lessonRepository: LessonRepository,
    private val courseContext: CourseContext
) : GetSectionLessonsWithStatisticsUseCase {
    override suspend fun execute(sectionId: Long): List<Lesson> {
        return lessonRepository
            .getSectionLessonsWithStatistics(
                courseCode = courseContext.getCourseCode(), sectionId = sectionId
            )
            .toDomainLessons()
    }
}