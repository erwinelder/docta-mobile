package cz.cvut.docta.course.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Dao
interface CourseRemoteDao {

    @Upsert
    suspend fun upsertCourses(courses: List<CourseRemoteEntity>)

    @Query("SELECT * FROM course_remote WHERE updateTime > :timestamp")
    suspend fun getCoursesAfterTimestamp(timestamp: Long): List<CourseRemoteEntity>

}