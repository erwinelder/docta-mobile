package cz.cvut.docta.core.data.database

import androidx.room.RoomDatabase

fun getLocalDatabaseBuilder(): RoomDatabase.Builder<AppLocalDatabase> {
    return getDatabaseBuilder<AppLocalDatabase>("docta.db")
}