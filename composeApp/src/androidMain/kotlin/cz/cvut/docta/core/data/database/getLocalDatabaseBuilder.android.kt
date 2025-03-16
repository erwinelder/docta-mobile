package cz.cvut.docta.core.data.database

import android.content.Context
import androidx.room.RoomDatabase

fun getLocalDatabaseBuilder(context: Context): RoomDatabase.Builder<AppLocalDatabase> {
    return getDatabaseBuilder<AppLocalDatabase>(context = context, databaseName = "docta.db")
}