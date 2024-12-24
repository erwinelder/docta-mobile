package cz.cvut.docta.section.domain.model

data class CourseSection(
    val id: Long,
    val name: String,
    val statistics: CourseSectionStatistics
)
