package cz.cvut.docta.core.data.database

import android.content.Context
import androidx.room.RoomDatabase

fun getLocalDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    return getDatabaseBuilder<AppDatabase>(context = context, databaseName = "docta.db")
}