package cz.cvut.docta.lessonSession.domain.model

data class QuestionMaterials(
    val id: Int,
    val text: String,
    val questionIds: List<Long>
)
