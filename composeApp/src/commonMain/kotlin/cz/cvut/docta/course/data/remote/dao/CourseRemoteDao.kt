package cz.cvut.docta.course.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Dao
interface CourseRemoteDao {

    @Query("SELECT * FROM course_remote WHERE updateTime > :timestamp")
    suspend fun getCoursesAfterTimestamp(timestamp: Long): List<CourseRemoteEntity>

}