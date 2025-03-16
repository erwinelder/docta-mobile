package cz.cvut.docta.core.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppLocalDatabaseConstructor : RoomDatabaseConstructor<AppLocalDatabase> {
    override fun initialize(): AppLocalDatabase
}