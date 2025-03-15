package cz.cvut.docta.achievement.data.repository

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.achievement.data.model.UserAchievementDto

class AchievementRemoteRepository: AchievementRepository {

    override suspend fun getAchievements(): List<AchievementDto> {
        // TODO-ACHIEVEMENTS: Implement fetching achievements from the server
        return emptyList()
    }

    override suspend fun getUserAchievements(userId: Long): List<UserAchievementDto> {
        // TODO-ACHIEVEMENTS: Implement fetching user achievements from the server
        return emptyList()
    }
}