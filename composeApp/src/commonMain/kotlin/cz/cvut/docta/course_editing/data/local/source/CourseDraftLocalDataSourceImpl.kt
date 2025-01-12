package cz.cvut.docta.course_editing.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.course_editing.data.local.dao.CourseDraftDao
import cz.cvut.docta.course_editing.data.model.CourseDraftEntity

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

fun courseDraftLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): CourseDraftLocalDataSource {
    return CourseDraftLocalDataSourceImpl(
        dao = appLocalDatabase.courseEditingDao()
    )
}