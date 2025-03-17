package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.lesson.data.local.model.LessonType
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithUserStats
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class LessonRemoteRepository(
    private val userContext: UserContext
) : LessonRepository {

    override suspend fun getLessonType(
        courseCode: String,
        sectionId: Long,
        lessonId: Long
    ): LessonType? {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/lessons/$lessonId/type"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<LessonType>(string = response.bodyAsText())
            } else {
                println("Error during fetching lesson type: ${response.status}")
                null
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            null
        }
    }

    override suspend fun getDefaultLesson(
        courseCode: String,
        sectionId: Long,
        lessonId: Long
    ): LessonDetails.Default? {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/lessons/default/$lessonId"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<LessonDetails.Default>(string = response.bodyAsText())
            } else {
                println("Error during fetching default lesson: ${response.status}")
                null
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            null
        }
    }

    override suspend fun getSectionLessons(
        courseCode: String,
        sectionId: Long
    ): List<LessonDetails> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/sections/$sectionId/lessons"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<LessonDetails>>(string = response.bodyAsText())
            } else {
                println("Error during fetching lessons: ${response.status}")
                emptyList()
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getSectionLessonsWithStatistics(
        courseCode: String,
        sectionId: Long
    ): List<LessonDetailsWithUserStats> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/sections/$sectionId/lessons-with-user-stats"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<LessonDetailsWithUserStats>>(string = response.bodyAsText())
            } else {
                println("Error during fetching lessons: ${response.status}")
                emptyList()
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            emptyList()
        }
    }

}