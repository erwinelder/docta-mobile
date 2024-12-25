package cz.cvut.docta.section.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.section.data.model.SectionEntity

@Dao
interface SectionDao {

    @Query("SELECT * FROM section WHERE id = :id")
    suspend fun getSection(id: Long): SectionEntity?

    @Query("SELECT * FROM section WHERE courseCode = :courseCode")
    suspend fun getCourseSections(courseCode: String): List<SectionEntity>

}