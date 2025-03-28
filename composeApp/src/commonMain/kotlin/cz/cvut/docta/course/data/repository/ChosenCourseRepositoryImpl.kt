package cz.cvut.docta.course.data.repository

import cz.cvut.docta.core.data.database.AppDatabase
import cz.cvut.docta.course.data.dao.ChosenCourseDao
import cz.cvut.docta.course.data.model.ChosenCourseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class ChosenCourseRepositoryImpl(
    private val dao: ChosenCourseDao
) : ChosenCourseRepository {

    override suspend fun upsertCourse(course: ChosenCourseEntity) {
        dao.upsertCourse(course = course)
    }

    override suspend fun deleteAndUpsertCourses(
        toDelete: List<ChosenCourseEntity>,
        toUpsert: List<ChosenCourseEntity>
    ) {
        dao.deleteAndUpsertCourses(toDelete = toDelete, toUpsert = toUpsert)
    }

    override fun getAllChosenCoursesFlow(): Flow<List<ChosenCourseEntity>> {
        return dao.getAllChosenCourses()
    }

    override suspend fun getAllChosenCourses(): List<ChosenCourseEntity> {
        return dao.getAllChosenCourses().firstOrNull().orEmpty()
    }

}

fun chosenCourseRepositoryFactory(appDatabase: AppDatabase): ChosenCourseRepository {
    return ChosenCourseRepositoryImpl(
        dao = appDatabase.chosenCourseDao()
    )
}