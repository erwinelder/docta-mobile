package cz.cvut.docta.lesson.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.section.data.model.SectionEntity

@Entity(
    tableName = "lesson",
    foreignKeys = [
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonEntity(
    val sectionId: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val orderNum: Int,
    val name: String,
    val difficulty: String
)
