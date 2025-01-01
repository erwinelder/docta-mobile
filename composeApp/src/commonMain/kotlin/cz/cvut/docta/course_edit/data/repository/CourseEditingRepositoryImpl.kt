package cz.cvut.docta.course_edit.data.repository

import cz.cvut.docta.course_edit.data.local.dao.CourseEditingDao
import cz.cvut.docta.course_edit.data.model.CourseEditingEntity

class CourseEditingRepositoryImpl(private val dao: CourseEditingDao) : CourseEditingRepository {

    override suspend fun getCourseEditing(courseCode: String): CourseEditingEntity? {
        return dao.getCourseEditing(courseCode)
    }

    override suspend fun saveCourseEditing(courseEditingEntity: CourseEditingEntity) {
        dao.saveCourseEditing(courseEditingEntity)
    }
}