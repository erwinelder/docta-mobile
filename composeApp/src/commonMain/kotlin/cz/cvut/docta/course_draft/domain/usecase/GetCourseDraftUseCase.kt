package cz.cvut.docta.course_draft.domain.usecase

import cz.cvut.docta.course_draft.domain.model.CourseDraft

interface GetCourseDraftUseCase {
    suspend fun execute(courseCode: String): CourseDraft?
}