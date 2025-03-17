package cz.cvut.docta.course.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course.data.mapper.remoteDtoToLocalEntity
import cz.cvut.docta.course.data.remote.model.CourseRemoteDTO
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class CourseRemoteRepository(
    private val userContext: UserContext
) : CourseRepository {

    override suspend fun getAllCourses(): List<CourseEntity> {
        return emptyList()
    }

    override suspend fun getCourses(codes: List<String>): List<CourseEntity> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/courses"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
                setBody(codes)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<CourseRemoteDTO>>(string = response.bodyAsText())
                    .map { it.remoteDtoToLocalEntity() }
            } else {
                println("Error during fetching courses: ${response.status}")
                emptyList()
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getCourse(courseCode: String): CourseEntity? {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/courses/$courseCode"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<CourseRemoteDTO>(string = response.bodyAsText())
                    .remoteDtoToLocalEntity()
            } else {
                println("Error during fetching courses: ${response.status}")
                null
            }
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            null
        }
    }

    override suspend fun getCourseRemotely(courseCode: String): CourseEntity? {
        return getCourse(courseCode = courseCode)
    }

}