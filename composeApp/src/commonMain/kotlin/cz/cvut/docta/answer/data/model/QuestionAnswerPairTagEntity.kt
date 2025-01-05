package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.model.CourseEntity

@Entity(
    tableName = "question_answer_pair_tag",
    primaryKeys = ["courseCode", "tag"],
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionAnswerPairTagEntity(
    val courseCode: String,
    val tag: String
)
