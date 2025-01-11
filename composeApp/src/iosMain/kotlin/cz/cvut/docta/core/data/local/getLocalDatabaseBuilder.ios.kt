package cz.cvut.docta.core.data.local

import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.getDatabaseBuilder

fun getLocalDatabaseBuilder(): RoomDatabase.Builder<AppLocalDatabase> {
    return getDatabaseBuilder<AppLocalDatabase>("docta.db")
}