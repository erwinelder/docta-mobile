package cz.cvut.docta.course.domain.model

data class CourseSectionLightweight(
    val id: Long,
    val orderNum: Int,
    val name: String,
    val statistics: CourseSectionStatistics
)
