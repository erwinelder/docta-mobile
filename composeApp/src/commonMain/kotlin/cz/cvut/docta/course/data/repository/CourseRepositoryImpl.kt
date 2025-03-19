package cz.cvut.docta.course.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.course.data.model.CourseDto
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class CourseRepositoryImpl(
    private val userContext: UserContext
) : CourseRepository {

    override suspend fun getCourses(codes: List<String>): List<CourseDto> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/courses"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
                setBody(codes)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<CourseDto>>(string = response.bodyAsText())
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    override suspend fun getCourse(courseCode: String): CourseDto? {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/courses/$courseCode"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<CourseDto>(string = response.bodyAsText())
            } else {
                null
            }
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getCourseRemotely(courseCode: String): CourseDto? {
        return getCourse(courseCode = courseCode)
    }

}