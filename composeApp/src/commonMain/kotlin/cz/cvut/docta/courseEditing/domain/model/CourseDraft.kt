package cz.cvut.docta.courseEditing.domain.model

import cz.cvut.docta.course.domain.model.CourseLocale

data class CourseDraft(
    val code: String,
    val name: String,
    val locale: CourseLocale
)
