package cz.cvut.docta.section.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity

@Dao
interface SectionRemoteDao {

    @Upsert
    suspend fun upsertSections(sections: List<SectionRemoteEntity>)

    @Query("SELECT * FROM section_remote WHERE courseCode = :courseCode AND updateTime > :timestamp")
    suspend fun getSectionsAfterTimestamp(courseCode: String, timestamp: Long): List<SectionRemoteEntity>

}