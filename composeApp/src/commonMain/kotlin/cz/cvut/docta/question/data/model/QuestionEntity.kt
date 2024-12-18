package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
sealed class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    open val id: Long,
    open val text: String
)
