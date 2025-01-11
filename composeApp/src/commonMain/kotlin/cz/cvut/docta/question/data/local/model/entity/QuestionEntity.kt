package cz.cvut.docta.question.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val difficulty: String
)
