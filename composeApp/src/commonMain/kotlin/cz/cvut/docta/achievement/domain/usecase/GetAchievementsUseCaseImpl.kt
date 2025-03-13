package cz.cvut.docta.achievement.domain.usecase

import cz.cvut.docta.achievement.data.repository.AchievementRepository
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.mapper.achievementDataToDomainModel

class GetAchievementsUseCaseImpl(
    private val achievementRepository: AchievementRepository
) : GetAchievementsUseCase {

    override suspend fun execute(): List<Achievement> {
        val achievements = achievementRepository.getAchievements()
        // TODO-USER-MANAGEMENT: Implement
        val achievementsProgress = achievementRepository.getUserAchievements(0)

        return achievements.zip(achievementsProgress).map {
            achievementDataToDomainModel(
                achievementDto = it.first,
                achievementProgress = it.second
            )
        }
    }

}