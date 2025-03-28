package cz.cvut.docta.achievement.mapper

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName

fun achievementDataToDomainModel(
    achievementDto: AchievementDto
): Achievement {
    return Achievement(
        name = AchievementName.fromKey(achievementDto.achievementName),
        numberOfSteps = achievementDto.numberOfSteps,
        completedSteps = achievementDto.completedSteps
    )
}