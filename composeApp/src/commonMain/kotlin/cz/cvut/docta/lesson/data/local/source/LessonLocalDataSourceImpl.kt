package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.lesson.data.model.LessonEntity
import kotlinx.coroutines.flow.Flow

class LessonLocalDataSourceImpl(
    private val dao: LessonDao
) : LessonLocalDataSource {

    override fun getSectionLessons(sectionId: Long): Flow<List<LessonEntity>> {
        return dao.getSectionLessons(sectionId)
    }
}