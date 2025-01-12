package cz.cvut.docta.course_editing.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.cvut.docta.course_editing.data.model.CourseDraftEntity

@Dao
interface CourseDraftDao {

    @Query("SELECT * FROM course_draft WHERE code = :courseCode")
    suspend fun getCourseDraft(courseCode: String): CourseDraftEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourseDraft(courseDraftEntity: CourseDraftEntity)
}