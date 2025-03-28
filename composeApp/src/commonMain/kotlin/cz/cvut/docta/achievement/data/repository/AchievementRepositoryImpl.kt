package cz.cvut.docta.achievement.data.repository

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.auth.domain.model.UserContext

class AchievementRepositoryImpl(
    val userContext: UserContext
) : AchievementRepository {

    override suspend fun getAchievementsProgress(): List<AchievementDto> {
        // TODO-ACHIEVEMENTS: Implement fetching achievements from the server

        return listOf(
            AchievementDto(
                achievementName = "educational_marathon", completedSteps = 1, numberOfSteps = 1000
            ),
            AchievementDto(
                achievementName = "knowledge_is_power",
                completedSteps = 16,
                numberOfSteps = 32
            ),
            AchievementDto(
                achievementName = "first_step", completedSteps = 1, numberOfSteps = 1
            )
        )
    }
}