package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_pair")
data class AnswerPairEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val firstAnswerId: Long,
    val secondAnswerId: Long
)
