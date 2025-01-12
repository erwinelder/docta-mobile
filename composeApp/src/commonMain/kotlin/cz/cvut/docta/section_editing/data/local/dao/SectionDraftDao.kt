package cz.cvut.docta.section_editing.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.section_editing.data.model.SectionDraftEntity

@Dao
interface SectionDraftDao {

    @Query("SELECT * FROM section_draft WHERE id = :id")
    suspend fun getSectionDraft(id: Long): SectionDraftEntity?

    @Upsert
    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}