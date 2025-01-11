package cz.cvut.docta.core.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified T : RoomDatabase> getDatabaseBuilder(
    context: Context,
    databaseName: String
): RoomDatabase.Builder<T> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(databaseName)

    return Room.databaseBuilder<T>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
