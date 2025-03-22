package cz.cvut.docta.lesson.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.model.IconResByAppTheme
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.presentation.navigation.LessonSessionScreens
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper
import dev.icerock.moko.resources.StringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.one_step_questions_lesson_dark_icon
import docta.composeapp.generated.resources.one_step_questions_lesson_light_icon
import docta.composeapp.generated.resources.step_by_step_lesson_dark_icon
import docta.composeapp.generated.resources.step_by_step_lesson_light_icon
import docta.composeapp.generated.resources.test_lesson_dark_icon
import docta.composeapp.generated.resources.test_lesson_light_icon


fun LessonWithProgress.getLessonIconRes(): IconResByAppTheme {
    return when (this) {
        is LessonWithProgress.Default -> IconResByAppTheme(
            Res.drawable.one_step_questions_lesson_light_icon,
            Res.drawable.one_step_questions_lesson_dark_icon
        )
        is LessonWithProgress.StepByStep -> IconResByAppTheme(
            Res.drawable.step_by_step_lesson_light_icon,
            Res.drawable.step_by_step_lesson_dark_icon
        )
        is LessonWithProgress.Test -> IconResByAppTheme(
            Res.drawable.test_lesson_light_icon,
            Res.drawable.test_lesson_dark_icon
        )
    }
}


fun LessonDifficulty.asStringRes(): StringResource {
    return when (this) {
        LessonDifficulty.Easy -> SharedRes.strings.easy
        LessonDifficulty.Medium -> SharedRes.strings.medium
        LessonDifficulty.Hard -> SharedRes.strings.hard
    }
}

@Composable
fun LessonDifficulty.asColor(): Color {
    return when (this) {
        LessonDifficulty.Easy -> DoctaColors.easyDifficultyColor
        LessonDifficulty.Medium -> DoctaColors.mediumDifficultyColor
        LessonDifficulty.Hard -> DoctaColors.hardDifficultyColor
    }
}


fun LessonFilterType.asStringRes(): StringResource {
    return when (this) {
        LessonFilterType.OneStepQuestions -> SharedRes.strings.one_step
        LessonFilterType.StepByStep -> SharedRes.strings.step_by_step
        LessonFilterType.NotDone -> SharedRes.strings.not_done
        LessonFilterType.Tests -> SharedRes.strings.tests
    }
}


fun QuestionAndAnswersWrapper.getLessonScreenToNavigateTo(): LessonSessionScreens {
    return when (this) {
        is QuestionAndAnswersWrapper.OpenAnswer -> LessonSessionScreens.OpenAnswerQuestion
        is QuestionAndAnswersWrapper.FillInBlanks -> LessonSessionScreens.FillInBlanksQuestion
        is QuestionAndAnswersWrapper.AnswerOptions -> LessonSessionScreens.AnswerOptionsQuestion
        is QuestionAndAnswersWrapper.QuestionAnswerPairs -> LessonSessionScreens.QuestionAnswerPairsQuestion
        is QuestionAndAnswersWrapper.StepByStep -> LessonSessionScreens.StepByStepQuestion
    }
}
