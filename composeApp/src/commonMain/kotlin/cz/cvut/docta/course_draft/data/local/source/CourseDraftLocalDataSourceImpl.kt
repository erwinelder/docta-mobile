package cz.cvut.docta.course_draft.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.course_draft.data.local.dao.CourseDraftDao
import cz.cvut.docta.course_draft.data.model.CourseDraftEntity

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

fun courseDraftLocalDataSourceFactory(
    appLocalDatabase: AppLocalDatabase
): CourseDraftLocalDataSource {
    return CourseDraftLocalDataSourceImpl(
        dao = appLocalDatabase.courseEditingDao()
    )
}