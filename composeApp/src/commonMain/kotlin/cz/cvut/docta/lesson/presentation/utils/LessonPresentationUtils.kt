package cz.cvut.docta.lesson.presentation.utils

import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.model.IconResByAppTheme
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
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
        is QuestionAndAnswersWrapper.CategorizationQuestion -> LessonSessionScreens.CategorizationQuestion
        is QuestionAndAnswersWrapper.QuestionAnswerPairs -> LessonSessionScreens.QuestionAnswerPairsQuestion
        is QuestionAndAnswersWrapper.StepByStep -> LessonSessionScreens.StepByStepQuestion
    }
}
