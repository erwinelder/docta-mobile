package cz.cvut.docta.courseEditing.domain.usecase

import cz.cvut.docta.courseEditing.domain.model.CourseDraft

interface GetCourseDraftUseCase {
    suspend fun execute(courseCode: String): CourseDraft?
}