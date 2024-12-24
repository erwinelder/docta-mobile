package cz.cvut.docta.lesson.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.lesson.data.model.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lesson WHERE section_id = :sectionId")
    fun getSectionLessons(sectionId: Long): Flow<List<LessonEntity>>

}