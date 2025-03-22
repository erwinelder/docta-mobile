package cz.cvut.docta.section.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.section.data.model.SectionDto
import cz.cvut.docta.section.data.model.SectionWithProgressDto
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class SectionRepositoryImpl(
    private val userContext: UserContext
) : SectionRepository {

    override suspend fun getSections(courseCode: String): List<SectionDto> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/courses/$courseCode/sections"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<SectionDto>>(string = response.bodyAsText())
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    override suspend fun getSection(
        courseCode: String,
        sectionId: Int
    ): SectionDto? {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/sections/$sectionId"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<SectionDto>(string = response.bodyAsText())
            } else {
                null
            }
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getSectionsWithProgress(courseCode: String): List<SectionWithProgressDto> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/courses/$courseCode/sections-with-progress"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<SectionWithProgressDto>>(string = response.bodyAsText())
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

}