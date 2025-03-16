package cz.cvut.docta.courseEditing.data.local.source

import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

interface CourseDraftRemoteDataSource {

    suspend fun getCourseDraft(courseCode: String): CourseDraftEntity?

    suspend fun saveCourseDraft(courseDraftEntity: CourseDraftEntity)

}
