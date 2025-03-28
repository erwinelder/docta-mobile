package cz.cvut.docta.courseEditing.data.repository

import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

interface CourseDraftRepository {

    suspend fun getCourseDraft(courseCode: String): CourseDraftEntity?

    suspend fun saveCourseDraft(courseDraft: CourseDraftEntity)

}