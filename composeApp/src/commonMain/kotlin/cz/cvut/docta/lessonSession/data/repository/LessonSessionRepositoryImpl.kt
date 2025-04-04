package cz.cvut.docta.lessonSession.data.repository

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.data.model.CompletedLessonStatsDto
import cz.cvut.docta.lessonSession.data.model.answer.AnswerCheckResultDto
import cz.cvut.docta.lessonSession.data.model.answer.AnswerInputDto
import cz.cvut.docta.lessonSession.data.model.QuestionWrapperDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto
import io.ktor.client.request.get
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
    ): List<QuestionWrapperDto> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/lesson-session/generate"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
                setBody(sessionOptions)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<QuestionWrapperDto>>(
                    string = response.bodyAsText()
                )
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    override suspend fun checkAnswer(
        answerInput: AnswerInputDto
    ): ResultData<AnswerCheckResultDto, LessonSessionError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/lesson-session/check-answer"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
                setBody(answerInput)
            }

            if (response.status == HttpStatusCode.OK) {
                ResultData.Success(
                    Json.decodeFromString<AnswerCheckResultDto>(string = response.bodyAsText())
                )
            } else {
                ResultData.Error(LessonSessionError.AnswerCheckError)
            }
        } catch (_: Exception) {
            ResultData.Error(LessonSessionError.AnswerCheckError)
        }
    }

    override suspend fun finishLessonSession(): ResultData<CompletedLessonStatsDto, LessonSessionError> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/lesson-session/finish"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                ResultData.Success(
                    Json.decodeFromString<CompletedLessonStatsDto>(string = response.bodyAsText())
                )
            } else {
                ResultData.Error(LessonSessionError.LessonSessionFinishingError)
            }
        } catch (_: Exception) {
            ResultData.Error(LessonSessionError.LessonSessionFinishingError)
        }
    }

    override suspend fun deleteLessonSession(): ResultData<Unit, LessonSessionError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/lesson-session/delete"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                ResultData.Success(Unit)
            } else {
                ResultData.Error(LessonSessionError.LessonSessionDeletionError)
            }
        } catch (_: Exception) {
            ResultData.Error(LessonSessionError.LessonSessionDeletionError)
        }
    }

}