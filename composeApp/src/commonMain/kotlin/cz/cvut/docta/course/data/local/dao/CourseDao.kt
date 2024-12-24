package cz.cvut.docta.course.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.course.data.model.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM course")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM course WHERE code = :courseCode")
    fun getCourse(courseCode: String): CourseEntity?

}