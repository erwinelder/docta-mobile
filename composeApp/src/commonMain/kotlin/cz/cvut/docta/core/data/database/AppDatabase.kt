package cz.cvut.docta.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.cvut.docta.course.data.dao.ChosenCourseDao
import cz.cvut.docta.course.data.model.ChosenCourseEntity
import cz.cvut.docta.courseEditing.data.local.dao.CourseDraftDao
import cz.cvut.docta.courseEditing.data.model.CourseDraftEntity
import cz.cvut.docta.sectionEditing.data.local.dao.SectionDraftDao
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        ChosenCourseEntity::class,
        CourseDraftEntity::class,

        SectionDraftEntity::class
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chosenCourseDao(): ChosenCourseDao
    abstract fun courseEditingDao(): CourseDraftDao
    abstract fun sectionEditingDao(): SectionDraftDao
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
//        .addMigrations()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
