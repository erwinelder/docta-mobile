package cz.cvut.docta.section.data.remote.source

import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity

interface SectionRemoteDataSource {

    suspend fun getUpdateTime(courseCode: String): Long?

    suspend fun saveUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getSectionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<SectionRemoteEntity>

}