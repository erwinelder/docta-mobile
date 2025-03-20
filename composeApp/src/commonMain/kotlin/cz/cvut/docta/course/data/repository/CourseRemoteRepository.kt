package cz.cvut.docta.course.data.repository

import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course.data.remote.model.CourseRemoteDTO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class CourseRemoteRepository : CourseRepository {

    override suspend fun getAllCourses(): List<CourseEntity> {
        return try {
            val response = httpClient.get(
                urlString = "https://docta-backend-adh0f0hsebb5epg7.northeurope-01.azurewebsites.net/courses"
            ) {
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<CourseRemoteDTO>>(string = response.bodyAsText()).map {
                    CourseEntity(code = it.code, locale = it.locale, name = it.name)
                }
            } else {
                println("Error during fetching courses: ${response.status}")
                emptyList()
            }
        } catch (e: Exception) {
            println("Connection error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getCourse(courseCode: String): CourseEntity? {
        return try {
            val response = httpClient.get(
                urlString = "https://docta-backend-adh0f0hsebb5epg7.northeurope-01.azurewebsites.net/courses/$courseCode"
            ) {
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<CourseRemoteDTO>(string = response.bodyAsText()).let {
                    CourseEntity(code = it.code, locale = it.locale, name = it.name)
                }
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