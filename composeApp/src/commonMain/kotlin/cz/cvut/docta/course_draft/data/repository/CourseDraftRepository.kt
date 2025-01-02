package cz.cvut.docta.course_draft.data.repository

import cz.cvut.docta.course_draft.data.model.CourseDraftEntity

interface CourseDraftRepository {

    suspend fun getCourseEditing(courseCode: String): CourseDraftEntity?

    suspend fun saveCourseEditing(courseDraftEntity: CourseDraftEntity)
}