package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.model.CourseSectionEntity
import cz.cvut.docta.course.data.model.LessonEntity

@Entity(
    tableName = "section_to_lesson",
    primaryKeys = ["sectionId", "lessonId"],
    foreignKeys = [
        ForeignKey(
            entity = CourseSectionEntity::class,
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
data class SectionToLessonEntity(
    val sectionId: Int,
    val lessonId: Long
)
