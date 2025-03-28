package cz.cvut.docta.sectionEditing.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

@Dao
interface SectionDraftDao {

    @Query("SELECT * FROM section_draft WHERE id = :id")
    suspend fun getSectionDraft(id: Int): SectionDraftEntity?

    @Query("SELECT * FROM section_draft WHERE courseCode = :courseCode")
    suspend fun getSectionDrafts(courseCode: String): List<SectionDraftEntity>

    @Upsert
    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}