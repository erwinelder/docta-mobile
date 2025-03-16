package cz.cvut.docta.achievement.data.repository

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.achievement.data.model.UserAchievementDto

interface AchievementRepository {

    suspend fun getAchievements(): List<AchievementDto>

    suspend fun getUserAchievements(userId: Int): List<UserAchievementDto>

}