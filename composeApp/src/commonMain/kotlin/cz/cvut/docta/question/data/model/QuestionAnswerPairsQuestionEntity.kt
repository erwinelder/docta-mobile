package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "question_answer_pairs_question"
)
data class QuestionAnswerPairsQuestionEntity(
    @PrimaryKey
    val questionId: Long
)
