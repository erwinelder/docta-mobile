package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.model.CourseEntity

@Entity(
    tableName = "question_tag",
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
data class QuestionTagEntity(
    val courseCode: String,
    val tag: String
)
