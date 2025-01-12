package cz.cvut.docta.course_editing.domain.usecase

import cz.cvut.docta.course_editing.domain.model.CourseDraft

interface GetCourseDraftUseCase {
    suspend fun execute(courseCode: String): CourseDraft?
}