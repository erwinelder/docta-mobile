package cz.cvut.docta.courseEditing.mapper

import cz.cvut.docta.course.data.model.CourseDto
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course.mapper.toDomainModel
import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity
import cz.cvut.docta.courseEditing.domain.model.CourseDraft


fun CourseDraftEntity.toDomainModel(): CourseDraft? {
    val locale = CourseLocale.fromLangCode(locale) ?: return null

    return CourseDraft(
        code = code,
        locale = locale,
        name = name
    )
}

fun CourseDraft.toDataModel(): CourseDraftEntity {
    return CourseDraftEntity(
        code = code,
        locale = locale.langCode,
        name = name
    )
}


fun CourseDto.toCourseDraft(): CourseDraft? {
    return CourseDraft(
        code = code,
        locale = locale.toDomainModel(),
        name = name
    )
}