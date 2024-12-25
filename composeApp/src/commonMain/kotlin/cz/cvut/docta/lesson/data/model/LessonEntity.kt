package cz.cvut.docta.lesson.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.cvut.docta.lesson.domain.model.LessonDifficulty

@Entity(tableName = "lesson")
data class LessonEntity(
    // TODO-LESSON
    @PrimaryKey(autoGenerate = true)
    val sectionId: Long,
    val id: Long,
    val name: String,
    val isDone: Boolean,
    val difficulty: LessonDifficulty? = null, // Optional for TestLesson
    val description: String? = null // Optional for StepByStepLesson
)
