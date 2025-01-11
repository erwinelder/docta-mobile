package cz.cvut.docta.core.data.remote

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppRemoteDatabaseConstructor : RoomDatabaseConstructor<AppRemoteDatabase> {
    override fun initialize(): AppRemoteDatabase
}
