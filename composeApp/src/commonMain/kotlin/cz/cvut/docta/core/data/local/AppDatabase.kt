package cz.cvut.docta.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.model.LocaleEntity
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.data.model.CourseSectionEntity

@Database(
    entities = [
        CourseSectionEntity::class,
        LocaleEntity::class,
        CourseEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
}

