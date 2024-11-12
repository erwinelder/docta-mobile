package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.data.model.CourseSectionEntity

@Entity(
    tableName = "course_to_section",
    primaryKeys = ["courseCode", "sectionId"],
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CourseSectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CourseToSectionEntity(
    val courseCode: String,
    val sectionId: Int
)
