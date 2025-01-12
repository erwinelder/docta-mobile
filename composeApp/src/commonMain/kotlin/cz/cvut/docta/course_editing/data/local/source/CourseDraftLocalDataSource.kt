package cz.cvut.docta.course_editing.data.local.source

import cz.cvut.docta.course_editing.data.model.CourseDraftEntity

interface CourseDraftLocalDataSource {

    suspend fun getCourseDraft(courseCode: String): CourseDraftEntity?

    suspend fun saveCourseDraft(courseDraftEntity: CourseDraftEntity)

}