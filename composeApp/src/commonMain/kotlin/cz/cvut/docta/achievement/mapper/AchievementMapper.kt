package cz.cvut.docta.achievement.mapper

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName

fun AchievementDto.toDomainModel(): Achievement {
    return Achievement(
        name = AchievementName.fromKey(this.achievementName),
        numberOfSteps = this.numberOfSteps,
        completedSteps = this.completedSteps
    )
}