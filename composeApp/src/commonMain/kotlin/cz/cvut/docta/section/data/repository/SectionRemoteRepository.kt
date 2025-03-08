package cz.cvut.docta.section.data.repository

import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.data.mapper.remoteDtoToLocalEntity
import cz.cvut.docta.section.data.remote.model.SectionRemoteDTO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class SectionRemoteRepository : SectionRepository {

    override suspend fun getSections(courseCode: String): List<SectionEntity> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/courses/$courseCode/sections"
            ) {
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<SectionRemoteDTO>>(string = response.bodyAsText())
                    .map { it.remoteDtoToLocalEntity() }
            } else {
                println("Error during fetching sections: ${response.status}")
                emptyList()
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getSection(
        courseCode: String,
        sectionId: Long
    ): SectionEntity? {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/sections/$sectionId"
            ) {
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<SectionRemoteDTO>(string = response.bodyAsText())
                    .remoteDtoToLocalEntity()
            } else {
                println("Error during fetching section: ${response.status}")
                null
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            null
        }
    }

}