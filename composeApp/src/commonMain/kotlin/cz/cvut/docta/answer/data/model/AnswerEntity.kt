package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer")
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)
