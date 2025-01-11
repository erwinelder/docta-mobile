package cz.cvut.docta.question.data.remote.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "question_answer_pairs_question_remote",
    foreignKeys = [
        ForeignKey(
            entity = QuestionRemoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionAnswerPairsQuestionRemoteEntity(
    @PrimaryKey
    val questionId: Long
)
