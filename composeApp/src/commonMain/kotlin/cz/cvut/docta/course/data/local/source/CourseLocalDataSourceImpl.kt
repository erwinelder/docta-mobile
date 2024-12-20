package cz.cvut.docta.course.data.local.source

import cz.cvut.docta.course.data.local.dao.CourseDao
import cz.cvut.docta.course.data.model.CourseEntity
import kotlinx.coroutines.flow.Flow

class CourseLocalDataSourceImpl(
    private val dao: CourseDao
) : CourseLocalDataSource {

    override fun getAllCourses(): Flow<List<CourseEntity>> {
        return dao.getAllCourses()
    }

}