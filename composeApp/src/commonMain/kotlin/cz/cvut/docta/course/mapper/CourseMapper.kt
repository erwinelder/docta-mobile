package cz.cvut.docta.course.mapper

import cz.cvut.docta.course.data.model.CourseDto
import cz.cvut.docta.course.data.model.CourseLocaleDto
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseLocale


fun CourseLocaleDto.toDomainModel(): CourseLocale {
    return when (this) {
        CourseLocaleDto.English -> CourseLocale.English
        CourseLocaleDto.Czech -> CourseLocale.Czech
    }
}


fun List<CourseDto>.toDomainModels(): List<Course> {
    return mapNotNull { it.toDomainModel() }
}

fun CourseDto.toDomainModel(): Course? {
    return Course(
        code = code,
        locale = locale.toDomainModel(),
        name = name,
        sectionCount = sectionCount
    )
}
