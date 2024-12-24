package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.data.model.toDomainModel
import cz.cvut.docta.course.domain.model.CourseLightweight
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl(
    private val localSource: CourseLocalDataSource
) : CourseRepository {

    override suspend fun getAllCourses(): List<CourseLightweight> {
        return localSource.getAllCourses()
            .map { entities -> entities.map { it.toDomainModel() } }
            .first()
    }

    override suspend fun getCourse(courseCode: String): CourseEntity? {
        return localSource.getCourse(courseCode)
    }
}
