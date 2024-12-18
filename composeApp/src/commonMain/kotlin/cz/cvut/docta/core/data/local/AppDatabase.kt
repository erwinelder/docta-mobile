package cz.cvut.docta.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.cvut.docta.core.data.model.LocaleEntity
import cz.cvut.docta.course.data.local.CourseDao
import cz.cvut.docta.course.data.local.CourseSectionDao
import cz.cvut.docta.course.data.local.LocaleDao
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.data.model.CourseSectionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

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

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
//        .addMigrations()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
