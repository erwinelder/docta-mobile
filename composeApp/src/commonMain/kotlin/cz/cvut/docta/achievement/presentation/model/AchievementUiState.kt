package cz.cvut.docta.achievement.presentation.model

import cz.cvut.docta.SharedRes
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName
import dev.icerock.moko.resources.StringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.achievement_placheholder_icon
import org.jetbrains.compose.resources.DrawableResource

data class AchievementUiState(
    val name: AchievementName,
    val title: StringResource,
    val description: StringResource,
    val percentage: Float,
    val icon: DrawableResource
) {

    companion object {

        fun fromAchievement(
            achievement: Achievement
        ): AchievementUiState {
            return AchievementUiState(
                name = achievement.name,
                title = when (achievement.name) {
                    AchievementName.FirstStep -> SharedRes.strings.achievement_first_step
                    AchievementName.ConfidentStart -> SharedRes.strings.achievement_first_step
                    AchievementName.EducationalMarathon -> SharedRes.strings.achievement_first_step
                    AchievementName.KnowledgeIsPower -> SharedRes.strings.achievement_first_step
                    AchievementName.TopStudent -> SharedRes.strings.achievement_first_step
                    AchievementName.FirstTest -> SharedRes.strings.achievement_first_step
                    AchievementName.OneHundredResult -> SharedRes.strings.achievement_first_step
                    AchievementName.SerialAStudent -> SharedRes.strings.achievement_first_step
                    AchievementName.ErrorWinner -> SharedRes.strings.achievement_first_step
                    AchievementName.SubjectMatterExpert -> SharedRes.strings.achievement_first_step
                    AchievementName.FlashStudent -> SharedRes.strings.achievement_first_step
                    AchievementName.FutureProfessor -> SharedRes.strings.achievement_first_step
                    AchievementName.NightCoder -> SharedRes.strings.achievement_first_step
                    AchievementName.IronWill -> SharedRes.strings.achievement_first_step
                    AchievementName.FacultyLegend -> SharedRes.strings.achievement_first_step
                },
                description = when (achievement.name) {
                    AchievementName.FirstStep -> SharedRes.strings.achievement_description_first_step
                    AchievementName.ConfidentStart -> SharedRes.strings.achievement_description_first_step
                    AchievementName.EducationalMarathon -> SharedRes.strings.achievement_description_first_step
                    AchievementName.KnowledgeIsPower -> SharedRes.strings.achievement_description_first_step
                    AchievementName.TopStudent -> SharedRes.strings.achievement_description_first_step
                    AchievementName.FirstTest -> SharedRes.strings.achievement_description_first_step
                    AchievementName.OneHundredResult -> SharedRes.strings.achievement_description_first_step
                    AchievementName.SerialAStudent -> SharedRes.strings.achievement_description_first_step
                    AchievementName.ErrorWinner -> SharedRes.strings.achievement_description_first_step
                    AchievementName.SubjectMatterExpert -> SharedRes.strings.achievement_description_first_step
                    AchievementName.FlashStudent -> SharedRes.strings.achievement_description_first_step
                    AchievementName.FutureProfessor -> SharedRes.strings.achievement_description_first_step
                    AchievementName.NightCoder -> SharedRes.strings.achievement_description_first_step
                    AchievementName.IronWill -> SharedRes.strings.achievement_description_first_step
                    AchievementName.FacultyLegend -> SharedRes.strings.achievement_description_first_step
                },
                percentage = achievement.numberOfSteps * 100f / achievement.completedSteps,
                icon = if (achievement.numberOfSteps < achievement.completedSteps) {
                    Res.drawable.achievement_placheholder_icon
                } else {
                    when (achievement.name) {
                        AchievementName.FirstStep -> Res.drawable.achievement_placheholder_icon
                        AchievementName.ConfidentStart -> Res.drawable.achievement_placheholder_icon
                        AchievementName.EducationalMarathon -> Res.drawable.achievement_placheholder_icon
                        AchievementName.KnowledgeIsPower -> Res.drawable.achievement_placheholder_icon
                        AchievementName.TopStudent -> Res.drawable.achievement_placheholder_icon
                        AchievementName.FirstTest -> Res.drawable.achievement_placheholder_icon
                        AchievementName.OneHundredResult -> Res.drawable.achievement_placheholder_icon
                        AchievementName.SerialAStudent -> Res.drawable.achievement_placheholder_icon
                        AchievementName.ErrorWinner -> Res.drawable.achievement_placheholder_icon
                        AchievementName.SubjectMatterExpert -> Res.drawable.achievement_placheholder_icon
                        AchievementName.FlashStudent -> Res.drawable.achievement_placheholder_icon
                        AchievementName.FutureProfessor -> Res.drawable.achievement_placheholder_icon
                        AchievementName.NightCoder -> Res.drawable.achievement_placheholder_icon
                        AchievementName.IronWill -> Res.drawable.achievement_placheholder_icon
                        AchievementName.FacultyLegend -> Res.drawable.achievement_placheholder_icon
                    }
                }
            )
        }

    }

}