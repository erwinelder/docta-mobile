package cz.cvut.docta.core.data.local

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppLocalDatabaseConstructor : RoomDatabaseConstructor<AppLocalDatabase> {
    override fun initialize(): AppLocalDatabase
}
