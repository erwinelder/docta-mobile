package cz.cvut.docta.core.data.remote

import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.database.getDatabaseBuilder

fun getRemoteDatabaseBuilder(): RoomDatabase.Builder<AppRemoteDatabase> {
    return getDatabaseBuilder<AppRemoteDatabase>("docta_remote.db")
}