package cz.cvut.docta.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.cvut.docta.course.data.local.dao.CourseDao
import cz.cvut.docta.section.data.local.dao.SectionDao
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.section.data.model.SectionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        CourseEntity::class,
        SectionEntity::class,
        // TODO-LESSON
    ],
    version = 1
)
@ConstructedBy(AppLocalDatabaseConstructor::class)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun sectionDao(): SectionDao
    abstract fun lessonDao(): LessonDao
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppLocalDatabase>): AppLocalDatabase {
    return builder
//        .addMigrations()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
