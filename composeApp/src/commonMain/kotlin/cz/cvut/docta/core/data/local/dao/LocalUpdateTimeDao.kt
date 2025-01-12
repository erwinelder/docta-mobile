package cz.cvut.docta.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.core.data.local.model.LocalUpdateTime

@Dao
interface LocalUpdateTimeDao {

    @Query("""
        SELECT updateTime FROM local_update_time
        WHERE tableName = :tableName AND courseCode = :courseCode  
    """)
    suspend fun getUpdateTime(tableName: String, courseCode: String = ""): Long?

    @Upsert
    suspend fun saveUpdateTime(updateTime: LocalUpdateTime)

    suspend fun saveUpdateTime(tableName: String, updateTime: Long, courseCode: String = "") {
        saveUpdateTime(
            updateTime = LocalUpdateTime(
                tableName = tableName,
                courseCode = courseCode,
                updateTime = updateTime
            )
        )
    }

}