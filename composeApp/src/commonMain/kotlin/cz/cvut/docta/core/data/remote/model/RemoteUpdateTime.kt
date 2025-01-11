package cz.cvut.docta.core.data.remote.model

import androidx.room.Entity

@Entity(
    tableName = "remote_update_time",
    primaryKeys = ["tableName", "courseCode"]
)
data class RemoteUpdateTime(
    val tableName: String,
    val courseCode: String,
    val updateTime: Long
)
