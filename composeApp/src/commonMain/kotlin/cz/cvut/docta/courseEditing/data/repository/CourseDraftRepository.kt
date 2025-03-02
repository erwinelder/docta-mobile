package cz.cvut.docta.courseEditing.data.repository

import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

interface CourseDraftRepository {

    suspend fun getCourseEditing(courseCode: String): CourseDraftEntity?

    suspend fun saveCourseEditing(courseDraftEntity: CourseDraftEntity)
}