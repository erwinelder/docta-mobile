package cz.cvut.docta.course_edit.domain.usecase

import cz.cvut.docta.course_edit.data.model.CourseEditingEntity
import cz.cvut.docta.course_edit.data.repository.CourseEditingRepository

class CourseEditingUseCase(private val repository: CourseEditingRepository) {

    suspend fun getCourseEditing(courseCode: String): CourseEditingEntity? {
        return repository.getCourseEditing(courseCode)
    }

    suspend fun saveCourseEditing(courseEditingEntity: CourseEditingEntity) {
        repository.saveCourseEditing(courseEditingEntity)
    }
}