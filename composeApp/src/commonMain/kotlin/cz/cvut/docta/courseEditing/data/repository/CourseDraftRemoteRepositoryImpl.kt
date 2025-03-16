package cz.cvut.docta.courseEditing.data.repository

import cz.cvut.docta.courseEditing.data.local.source.CourseDraftRemoteDataSource
import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

class CourseDraftRemoteRepositoryImpl (
    private val remoteSource: CourseDraftRemoteDataSource,
) : CourseDraftRemoteRepository {

    override suspend fun getCourseEditing(courseCode: String): CourseDraftEntity? {
        return remoteSource.getCourseDraft(courseCode)
    }

    override suspend fun saveCourseEditing(courseDraftEntity: CourseDraftEntity) {
        remoteSource.saveCourseDraft(courseDraftEntity)
    }
}