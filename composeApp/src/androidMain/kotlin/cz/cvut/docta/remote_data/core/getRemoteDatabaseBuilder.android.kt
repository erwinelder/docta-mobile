package cz.cvut.docta.remote_data.core

import android.content.Context
import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.getDatabaseBuilder
import cz.cvut.docta.core.data.remote.AppRemoteDatabase

fun getRemoteDatabaseBuilder(context: Context): RoomDatabase.Builder<AppRemoteDatabase> {
    return getDatabaseBuilder<AppRemoteDatabase>(context = context, databaseName = "docta_remote.db")
}
