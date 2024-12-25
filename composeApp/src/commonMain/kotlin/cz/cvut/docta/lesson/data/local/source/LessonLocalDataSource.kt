package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.lesson.data.model.LessonEntity
import kotlinx.coroutines.flow.Flow

interface LessonLocalDataSource {
    suspend fun getSectionLessons(sectionId: Long): Flow<List<LessonEntity>>
}