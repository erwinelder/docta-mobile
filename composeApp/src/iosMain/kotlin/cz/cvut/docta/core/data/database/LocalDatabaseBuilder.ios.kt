package cz.cvut.docta.core.data.database

import androidx.room.RoomDatabase

fun getLocalDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return getDatabaseBuilder<AppDatabase>("docta.db")
}