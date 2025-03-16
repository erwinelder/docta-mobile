package cz.cvut.docta.courseEditing.data.local.source

import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.course.data.remote.dao.CourseRemoteDao
import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

class CourseDraftRemoteDataSourceImpl(
    private val dao: CourseRemoteDao
) : CourseDraftRemoteDataSource {

    override suspend fun getCourseDraft(courseCode: String): CourseDraftEntity? {
        // TODO-Remote-COURSE
        return null
    }

    override suspend fun saveCourseDraft(courseDraftEntity: CourseDraftEntity) {
        // TODO-Remote-COURSE
    }

}

fun courseDraftRemoteDataSourceFactory(appRemoteDatabase: AppRemoteDatabase): CourseDraftRemoteDataSource {
    return CourseDraftRemoteDataSourceImpl(
        dao = appRemoteDatabase.courseRemoteDao()
    )
}