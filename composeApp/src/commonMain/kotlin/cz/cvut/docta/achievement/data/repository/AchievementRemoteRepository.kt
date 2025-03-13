package cz.cvut.docta.achievement.data.repository

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.achievement.data.model.UserAchievementDto

class AchievementRemoteRepository: AchievementRepository {

    override suspend fun getAchievements(): List<AchievementDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserAchievements(userId: Long): List<UserAchievementDto> {
        TODO("Not yet implemented")
    }
}