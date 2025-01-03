package cz.cvut.docta.section_draft.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.cvut.docta.section_draft.data.model.SectionDraftEntity

@Dao
interface SectionDraftDao {
    @Query("SELECT * FROM section_draft WHERE code = :courseCode")
    suspend fun getSectionDraft(id: Long): SectionDraftEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)
}