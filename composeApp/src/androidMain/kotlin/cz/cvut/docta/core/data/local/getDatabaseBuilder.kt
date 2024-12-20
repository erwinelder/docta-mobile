package cz.cvut.docta.core.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppLocalDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("docta.db")

    return Room.databaseBuilder<AppLocalDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
