package cz.cvut.docta.course.domain.model

data class CourseSectionLightweight(
    val id: Long,
    val name: String,
    val statistics: CourseSectionStatistics
)
