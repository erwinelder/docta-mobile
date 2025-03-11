package cz.cvut.docta.core.data.remote

import android.content.Context
import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.getDatabaseBuilder

fun getRemoteDatabaseBuilder(context: Context): RoomDatabase.Builder<AppRemoteDatabase> {
    return getDatabaseBuilder<AppRemoteDatabase>(context = context, databaseName = "docta_remote.db")
}
