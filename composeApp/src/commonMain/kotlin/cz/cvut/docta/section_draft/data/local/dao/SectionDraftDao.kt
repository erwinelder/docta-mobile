package cz.cvut.docta.section_draft.data.local.dao

import androidx.room.*
import cz.cvut.docta.section_draft.data.model.SectionDraftEntity

@Dao
interface SectionDraftDao {
    @Query("SELECT * FROM section_draft WHERE courseCode = :courseCode")
    suspend fun getSectionDraft(id: Long): SectionDraftEntity?

    @Upsert
    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)
}