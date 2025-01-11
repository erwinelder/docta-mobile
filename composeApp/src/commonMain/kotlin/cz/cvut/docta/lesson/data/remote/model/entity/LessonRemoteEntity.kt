package cz.cvut.docta.lesson.data.remote.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity

@Entity(
    tableName = "lesson_remote",
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SectionRemoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val sectionId: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val orderNum: Int,
    val name: String,
    val difficulty: String
)
