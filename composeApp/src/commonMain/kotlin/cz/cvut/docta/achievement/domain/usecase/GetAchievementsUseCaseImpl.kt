package cz.cvut.docta.achievement.domain.usecase

import cz.cvut.docta.achievement.data.repository.AchievementRepository
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName
import cz.cvut.docta.achievement.mapper.achievementDataToDomainModel

class GetAchievementsUseCaseImpl(
    private val achievementRepository: AchievementRepository,
) : GetAchievementsUseCase {

    override suspend fun execute(): List<Achievement> {
        val achievements = achievementRepository.getAchievementsProgress()

        return achievements
            .filter { achievement -> AchievementName.hasKey(achievement.achievementName) }
            .map { achievement ->
                achievementDataToDomainModel(
                    achievementDto = achievement
                )
            }
    }

}