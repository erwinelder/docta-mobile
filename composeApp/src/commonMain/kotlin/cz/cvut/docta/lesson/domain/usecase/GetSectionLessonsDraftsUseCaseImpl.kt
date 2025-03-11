package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.mapper.toLessonDraftList

class GetSectionLessonsDraftsUseCaseImpl(
    private val lessonRepository: LessonRepository,
    private val courseContext: CourseContext
) : GetSectionLessonsDraftsUseCase {
    override suspend fun execute(sectionId: Long): List<LessonDraft> {
        return lessonRepository
            .getSectionLessons(courseCode = courseContext.getCourseCode(), sectionId = sectionId)
            .sortedBy { it.orderNum }
            .toLessonDraftList()
    }
}