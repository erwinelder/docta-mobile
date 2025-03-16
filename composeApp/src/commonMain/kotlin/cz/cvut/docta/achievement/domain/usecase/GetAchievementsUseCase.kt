package cz.cvut.docta.achievement.domain.usecase

import cz.cvut.docta.achievement.domain.model.Achievement

interface GetAchievementsUseCase {
    suspend fun execute(): List<Achievement>
}