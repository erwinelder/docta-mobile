package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.section.data.model.SectionEntity
import cz.cvut.docta.lesson.data.model.LessonEntity

@Entity(
    tableName = "section_lesson_association",
    primaryKeys = ["sectionId", "lessonId"],
    foreignKeys = [
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SectionLessonAssociationEntity(
    val sectionId: Int,
    val lessonId: Long
)
