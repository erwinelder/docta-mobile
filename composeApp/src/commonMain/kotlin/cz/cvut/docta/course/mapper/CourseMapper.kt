package cz.cvut.docta.course.mapper

import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.domain.model.CourseLightweight

fun List<CourseEntity>.toCourseLightweightList() = map { it.toCourseLightweight() }

fun CourseEntity.toCourseLightweight(): CourseLightweight {
    return CourseLightweight(
        code = code,
        locale = locale,
        name = name
    )
}

fun CourseEntity.toDomainModel(): CourseLightweight {
    return CourseLightweight(
        code = this.code,
        locale = this.locale,
        name = this.name
    )
}

fun CourseLightweight.toEntity(): CourseEntity {
    return CourseEntity(
        code = this.code,
        locale = this.locale,
        name = this.name
    )
}