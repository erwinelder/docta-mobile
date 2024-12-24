package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.lesson.data.model.LessonEntity
import kotlinx.coroutines.flow.Flow

class LessonLocalDataSourceImpl(
    private val dao: LessonDao
) : LessonLocalDataSource {
    override suspend fun getSectionLessons(sectionId: Long): List<LessonEntity> {
        return dao.getSectionLessons(sectionId = sectionId)
    }
}

fun lessonLocalDataSourceFactory(
    appLocalDatabase: AppLocalDatabase
): LessonLocalDataSourceImpl {
    return LessonLocalDataSourceImpl(dao = appLocalDatabase.lessonDao())
}