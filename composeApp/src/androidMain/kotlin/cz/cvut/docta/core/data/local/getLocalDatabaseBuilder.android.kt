package cz.cvut.docta.core.data.local

import android.content.Context
import androidx.room.RoomDatabase
import cz.cvut.docta.core.data.getDatabaseBuilder

fun getLocalDatabaseBuilder(context: Context): RoomDatabase.Builder<AppLocalDatabase> {
    return getDatabaseBuilder<AppLocalDatabase>(context = context, databaseName = "docta.db")
}