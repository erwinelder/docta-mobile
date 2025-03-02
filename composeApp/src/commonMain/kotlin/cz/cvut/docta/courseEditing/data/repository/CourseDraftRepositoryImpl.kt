package cz.cvut.docta.courseEditing.data.repository

import cz.cvut.docta.courseEditing.data.local.source.CourseDraftLocalDataSource
import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

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