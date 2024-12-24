package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.model.CourseEntity
import kotlinx.coroutines.flow.Flow

class CourseRepositoryImpl(
    private val localSource: CourseLocalDataSource
) : CourseRepository {

    override fun getAllCourses(): Flow<List<CourseEntity>> {
        return localSource.getAllCourses()
    }

    override suspend fun getCourse(courseCode: String): CourseEntity? {
        return localSource.getCourse(courseCode = courseCode)
    }

}
