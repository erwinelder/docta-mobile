package cz.cvut.docta.section.domain.model

import cz.cvut.docta.course.domain.model.CourseSectionStatistics

data class CourseSection(
    val id: Long,
    val name: String,
    val statistics: CourseSectionStatistics
)
