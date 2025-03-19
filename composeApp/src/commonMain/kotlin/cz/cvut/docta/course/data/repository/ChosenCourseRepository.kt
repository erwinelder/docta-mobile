package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.model.ChosenCourseEntity
import kotlinx.coroutines.flow.Flow

interface ChosenCourseRepository {

    suspend fun upsertCourse(course: ChosenCourseEntity)

    suspend fun deleteAndUpsertCourses(
        toDelete: List<ChosenCourseEntity>,
        toUpsert: List<ChosenCourseEntity>
    )

    fun getAllChosenCoursesFlow(): Flow<List<ChosenCourseEntity>>

    suspend fun getAllChosenCourses(): List<ChosenCourseEntity>

}