package cz.cvut.docta.course_draft.domain.model

import cz.cvut.docta.course.domain.model.CourseLocale

data class CourseDraft(
    val code: String,
    val name: String,
    val locale: CourseLocale
)
