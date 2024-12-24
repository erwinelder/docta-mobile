package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.section.data.model.SectionEntity

@Entity(
    tableName = "course_section_association",
    primaryKeys = ["courseCode", "sectionId"],
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CourseSectionAssociationEntity(
    val courseCode: String,
    val sectionId: Int
)
