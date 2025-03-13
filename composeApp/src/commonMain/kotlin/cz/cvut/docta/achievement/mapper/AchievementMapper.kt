package cz.cvut.docta.achievement.mapper

import cz.cvut.docta.achievement.data.model.AchievementDto
import cz.cvut.docta.achievement.data.model.AchievementNameDto
import cz.cvut.docta.achievement.data.model.UserAchievementDto
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName

fun achievementDataToDomainModel(
    achievementDto: AchievementDto,
    achievementProgress: UserAchievementDto
): Achievement {
    return Achievement(
        name = achievementDto.name.toAchievementNameDomainModel(),
        title = achievementDto.title,
        percentage = achievementProgress.percentage,
        completed = achievementProgress.completed
    )
}

fun AchievementNameDto.toAchievementNameDomainModel(): AchievementName {
    return when (this) {
        AchievementNameDto.First -> AchievementName.First
        AchievementNameDto.Second -> AchievementName.Second
        AchievementNameDto.Third -> AchievementName.Third
        AchievementNameDto.Default -> AchievementName.Default
    }
}