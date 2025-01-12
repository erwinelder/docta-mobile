package cz.cvut.docta.core.data.local.model

import androidx.room.Entity

@Entity(
    tableName = "local_update_time",
    primaryKeys = ["tableName", "courseCode"]
)
data class LocalUpdateTime(
    val tableName: String,
    val courseCode: String,
    val updateTime: Long
)
