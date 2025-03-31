package cz.cvut.docta.achievement.data.repository

import cz.cvut.docta.achievement.data.model.AchievementDto

interface AchievementRepository {

    suspend fun getAchievementsProgress(): List<AchievementDto>

}