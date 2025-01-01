package cz.cvut.docta.course_edit.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.cvut.docta.course_edit.data.model.CourseEditingEntity

@Dao
interface CourseEditingDao {

    @Query("SELECT * FROM course_editing WHERE code = :courseCode")
    suspend fun getCourseEditing(courseCode: String): CourseEditingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourseEditing(courseEditingEntity: CourseEditingEntity)
}