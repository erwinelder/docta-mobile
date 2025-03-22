package cz.cvut.docta.section.domain.model

data class Section(
    val courseCode: String,
    val id: Int,
    val orderNum: Int,
    val name: String,
    val lessonCount: Int
)
