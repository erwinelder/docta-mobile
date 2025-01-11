package cz.cvut.docta.course.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cz.cvut.docta.course.data.local.model.CourseEntity

@Dao
interface CourseDao {

    @Upsert
    suspend fun upsertCourses(courses: List<CourseEntity>)

    @Delete
    suspend fun deleteCourses(courses: List<CourseEntity>)

    @Transaction
    suspend fun deleteAndUpsertCourses(toDelete: List<CourseEntity>, toUpsert: List<CourseEntity>) {
        deleteCourses(toDelete)
        upsertCourses(toUpsert)
    }

    @Query("SELECT * FROM course")
    suspend fun getAllCourses(): List<CourseEntity>

    @Query("SELECT * FROM course WHERE code = :courseCode")
    suspend fun getCourse(courseCode: String): CourseEntity?

}