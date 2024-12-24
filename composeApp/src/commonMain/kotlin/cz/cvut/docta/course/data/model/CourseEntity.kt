package cz.cvut.docta.course.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import cz.cvut.docta.core.data.model.LocaleEntity
import cz.cvut.docta.course.domain.model.CourseLightweight

@Entity(
    tableName = "course",
    foreignKeys = [
        ForeignKey(
            entity = LocaleEntity::class,
            parentColumns = ["code"],
            childColumns = ["locale"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["locale"])
    ]
)
data class CourseEntity(
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)

fun CourseEntity.toDomainModel(): CourseLightweight {
    return CourseLightweight(
        code = this.code,
        locale = this.locale,
        name = this.name
    )
}

fun CourseLightweight.toEntity(): CourseEntity {
    return CourseEntity(
        code = this.code,
        locale = this.locale,
        name = this.name
    )
}
