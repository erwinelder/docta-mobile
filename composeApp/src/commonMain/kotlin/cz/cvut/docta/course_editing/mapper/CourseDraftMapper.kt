package cz.cvut.docta.course_editing.mapper

import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course_editing.data.model.CourseDraftEntity
import cz.cvut.docta.course_editing.domain.model.CourseDraft


fun CourseDraftEntity.toDomain(): CourseDraft? {
    val locale = CourseLocale.entries.find { it.name == locale } ?: return null

    return CourseDraft(
        code = code,
        locale = locale,
        name = name
    )
}

fun CourseDraft.toEntity(): CourseDraftEntity {
    return CourseDraftEntity(
        code = code,
        locale = locale.name,
        name = name
    )
}


fun CourseEntity.toCourseDraft(): CourseDraft? {
    val locale = CourseLocale.entries.find { it.name == locale } ?: return null

    return CourseDraft(
        code = code,
        locale = locale,
        name = name
    )
}