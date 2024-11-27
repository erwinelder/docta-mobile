package cz.cvut.docta.course.domain.model

data class CourseSection(
    val id: Long,
    val orderNum: Int,
    val name: String,
    val statistics: CourseSectionStatistics,
    val lessons: List<LessonLightWeight>
)
