package cz.cvut.docta.course_edit.data.repository

import cz.cvut.docta.course_edit.data.model.CourseEditingEntity

interface CourseEditingRepository {

    suspend fun getCourseEditing(courseCode: String): CourseEditingEntity?

    suspend fun saveCourseEditing(courseEditingEntity: CourseEditingEntity)
}