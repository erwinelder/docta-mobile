package cz.cvut.docta.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.model.LocaleEntity
import cz.cvut.docta.course.data.local.CourseDao
import cz.cvut.docta.course.data.local.CourseSectionDao
import cz.cvut.docta.course.data.local.LocaleDao
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.data.model.CourseSectionEntity

@Database(
    entities = [
        CourseEntity::class,
        CourseSectionEntity::class,
        LocaleEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun courseSectionDao(): CourseSectionDao
    abstract fun localeDao(): LocaleDao
}