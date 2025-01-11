package cz.cvut.docta.remote_data.core

import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.getDatabaseBuilder
import cz.cvut.docta.core.data.remote.AppRemoteDatabase

fun getRemoteDatabaseBuilder(): RoomDatabase.Builder<AppRemoteDatabase> {
    return getDatabaseBuilder<AppRemoteDatabase>("docta_remote.db")
}