package cz.cvut.docta.courseEditing.data.local.source

import cz.cvut.docta.core.data.database.AppDatabase
import cz.cvut.docta.courseEditing.data.local.dao.CourseDraftDao
import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity

class CourseDraftLocalDataSourceImpl(
    private val dao: CourseDraftDao
) : CourseDraftLocalDataSource {

    override suspend fun getCourseDraft(courseCode: String): CourseDraftEntity? {
        return dao.getCourseDraft(courseCode)
    }

    override suspend fun saveCourseDraft(courseDraftEntity: CourseDraftEntity) {
        dao.saveCourseDraft(courseDraftEntity)
    }

}

fun courseDraftLocalDataSourceFactory(appDatabase: AppDatabase): CourseDraftLocalDataSource {
    return CourseDraftLocalDataSourceImpl(
        dao = appDatabase.courseEditingDao()
    )
}