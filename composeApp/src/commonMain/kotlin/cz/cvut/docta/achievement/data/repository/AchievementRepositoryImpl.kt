package cz.cvut.docta.achievement.data.repository

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class AchievementRepositoryImpl(
    val userContext: UserContext
) : AchievementRepository {

    override suspend fun getAchievementsProgress(): List<AchievementDto> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/achievement/progress"
            ) {
                header("Authorization", "Bearer ${userContext.getAuthToken()}")
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) {
                Json.decodeFromString<List<AchievementDto>>(string = response.bodyAsText())
            } else {
                emptyList()
            }
        } catch (_: Exception) {
            emptyList()
        }
    }
}