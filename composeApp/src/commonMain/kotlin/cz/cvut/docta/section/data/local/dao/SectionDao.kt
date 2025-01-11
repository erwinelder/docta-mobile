package cz.cvut.docta.section.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cz.cvut.docta.section.data.local.model.SectionEntity

@Dao
interface SectionDao {

    @Upsert
    suspend fun upsertSections(sections: List<SectionEntity>)

    @Delete
    suspend fun deleteSections(sections: List<SectionEntity>)

    @Transaction
    suspend fun deleteAndUpsertSections(toDelete: List<SectionEntity>, toUpsert: List<SectionEntity>) {
        deleteSections(toDelete)
        upsertSections(toUpsert)
    }

    @Query("SELECT * FROM section WHERE id = :id")
    suspend fun getSection(id: Long): SectionEntity?

    @Query("SELECT * FROM section WHERE courseCode = :courseCode")
    suspend fun getCourseSections(courseCode: String): List<SectionEntity>

}