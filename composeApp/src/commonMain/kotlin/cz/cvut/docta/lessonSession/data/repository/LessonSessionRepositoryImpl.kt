package cz.cvut.docta.lessonSession.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.lessonSession.data.model.QuestionWithCorrectAnswersDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class LessonSessionRepositoryImpl(
    private val userContext: UserContext
) : LessonSessionRepository {

    override suspend fun getDefaultQuestionsWithCorrectAnswers(
        sessionOptions: SessionOptionsDto
    ): List<QuestionWithCorrectAnswersDto> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/session-generation"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
                setBody(sessionOptions)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<QuestionWithCorrectAnswersDto>>(
                    string = response.bodyAsText()
                )
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

}