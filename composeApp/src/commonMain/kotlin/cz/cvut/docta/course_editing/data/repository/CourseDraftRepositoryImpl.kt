package cz.cvut.docta.course_editing.data.repository

import cz.cvut.docta.course_editing.data.local.source.CourseDraftLocalDataSource
import cz.cvut.docta.course_editing.data.model.CourseDraftEntity

class CourseDraftRepositoryImpl(
    private val localSource: CourseDraftLocalDataSource,
) : CourseDraftRepository {

    override suspend fun getCourseEditing(courseCode: String): CourseDraftEntity? {
        return localSource.getCourseDraft(courseCode)
    }

    override suspend fun saveCourseEditing(courseDraftEntity: CourseDraftEntity) {
        localSource.saveCourseDraft(courseDraftEntity)
    }
}