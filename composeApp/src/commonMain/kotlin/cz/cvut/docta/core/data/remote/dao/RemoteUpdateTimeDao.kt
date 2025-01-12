package cz.cvut.docta.core.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.core.data.remote.model.RemoteUpdateTime

@Dao
interface RemoteUpdateTimeDao {

    @Query("""
        SELECT updateTime FROM remote_update_time
        WHERE tableName = :tableName AND courseCode = :courseCode
    """)
    suspend fun getUpdateTime(tableName: String, courseCode: String = ""): Long?

    @Upsert
    suspend fun saveUpdateTime(updateTime: RemoteUpdateTime)

    suspend fun saveUpdateTime(tableName: String, updateTime: Long, courseCode: String = "") {
        saveUpdateTime(
            updateTime = RemoteUpdateTime(
                tableName = tableName,
                courseCode = courseCode,
                updateTime = updateTime
            )
        )
    }

}