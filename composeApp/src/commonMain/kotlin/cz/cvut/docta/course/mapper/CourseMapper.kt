package cz.cvut.docta.course.mapper

import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseLocale


fun List<CourseEntity>.toDomainModels() = mapNotNull { it.toDomainModel() }

fun CourseEntity.toDomainModel(): Course? {
    val locale = CourseLocale.fromString(locale) ?: return null

    return Course(
        code = code,
        locale = locale,
        name = name
    )
}
