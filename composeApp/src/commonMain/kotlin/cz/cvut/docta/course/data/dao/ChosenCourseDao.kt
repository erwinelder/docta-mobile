package cz.cvut.docta.course.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cz.cvut.docta.course.data.model.ChosenCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChosenCourseDao {

    @Upsert
    suspend fun upsertCourse(course: ChosenCourseEntity)

    @Upsert
    suspend fun upsertCourses(courses: List<ChosenCourseEntity>)

    @Delete
    suspend fun deleteCourses(courses: List<ChosenCourseEntity>)

    @Transaction
    suspend fun deleteAndUpsertCourses(
        toDelete: List<ChosenCourseEntity>,
        toUpsert: List<ChosenCourseEntity>
    ) {
        deleteCourses(toDelete)
        upsertCourses(toUpsert)
    }

    @Query("SELECT * FROM chosen_course")
    fun getAllChosenCourses(): Flow<List<ChosenCourseEntity>>

}