package cz.cvut.docta.course_draft.data.local.source

import cz.cvut.docta.course_draft.data.model.CourseDraftEntity

interface CourseDraftLocalDataSource {

    suspend fun getCourseDraft(courseCode: String): CourseDraftEntity?

    suspend fun saveCourseDraft(courseDraftEntity: CourseDraftEntity)

}