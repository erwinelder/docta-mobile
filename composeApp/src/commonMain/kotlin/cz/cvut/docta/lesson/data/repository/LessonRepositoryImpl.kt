package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.lesson.data.model.LessonDto
import cz.cvut.docta.lesson.data.model.LessonWithProgressDto
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class LessonRepositoryImpl(
    private val userContext: UserContext
) : LessonRepository {

    override suspend fun getLessons(
        courseCode: String,
        sectionId: Long
    ): List<LessonDto> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/sections/$sectionId/lessons"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<LessonDto>>(string = response.bodyAsText())
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    override suspend fun getLessonsWithProgress(
        courseCode: String,
        sectionId: Long
    ): List<LessonWithProgressDto> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/sections/$sectionId/lessons-with-user-progress"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<LessonWithProgressDto>>(string = response.bodyAsText())
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

}