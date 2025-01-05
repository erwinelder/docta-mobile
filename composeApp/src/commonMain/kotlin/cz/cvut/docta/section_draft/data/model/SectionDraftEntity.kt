package cz.cvut.docta.section_draft.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course_draft.data.model.CourseDraftEntity

@Entity(
    tableName = "section_draft",
    foreignKeys = [
        ForeignKey(
            entity = CourseDraftEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SectionDraftEntity(
    val courseCode: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)