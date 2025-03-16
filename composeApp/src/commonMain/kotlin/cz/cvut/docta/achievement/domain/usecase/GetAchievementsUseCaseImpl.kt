package cz.cvut.docta.achievement.domain.usecase

import cz.cvut.docta.achievement.data.repository.AchievementRepository
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.mapper.achievementDataToDomainModel
import cz.cvut.docta.auth.domain.model.UserContext

class GetAchievementsUseCaseImpl(
    private val achievementRepository: AchievementRepository,
    private val userContext: UserContext
) : GetAchievementsUseCase {
    override suspend fun execute(): List<Achievement> {
        val achievements = achievementRepository.getAchievements()
        val achievementsProgress = achievementRepository.getUserAchievements(
            userId = userContext.userId
        )

        return achievements.zip(achievementsProgress).map { (achievement, progress) ->
            achievementDataToDomainModel(
                achievementDto = achievement,
                achievementProgress = progress
            )
        }
    }
}