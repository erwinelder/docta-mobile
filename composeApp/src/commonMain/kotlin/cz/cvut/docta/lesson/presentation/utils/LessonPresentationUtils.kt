package cz.cvut.docta.lesson.presentation.utils

import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.presentation.model.IconResByAppTheme
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.easy
import docta.composeapp.generated.resources.hard
import docta.composeapp.generated.resources.medium
import docta.composeapp.generated.resources.not_done
import docta.composeapp.generated.resources.one_step
import docta.composeapp.generated.resources.one_step_questions_lesson_dark_icon
import docta.composeapp.generated.resources.one_step_questions_lesson_light_icon
import docta.composeapp.generated.resources.step_by_step
import docta.composeapp.generated.resources.step_by_step_lesson_dark_icon
import docta.composeapp.generated.resources.step_by_step_lesson_light_icon
import docta.composeapp.generated.resources.test_lesson_dark_icon
import docta.composeapp.generated.resources.test_lesson_light_icon
import docta.composeapp.generated.resources.tests
import org.jetbrains.compose.resources.StringResource


fun Lesson.getLessonIconRes(): IconResByAppTheme {
    return when (this) {
        is Lesson.OneStepQuestionsLesson -> IconResByAppTheme(
            Res.drawable.one_step_questions_lesson_light_icon,
            Res.drawable.one_step_questions_lesson_dark_icon
        )
        is Lesson.StepByStepLesson -> IconResByAppTheme(
            Res.drawable.step_by_step_lesson_light_icon,
            Res.drawable.step_by_step_lesson_dark_icon
        )
        is Lesson.TestLesson -> IconResByAppTheme(
            Res.drawable.test_lesson_light_icon,
            Res.drawable.test_lesson_dark_icon
        )
    }
}


fun LessonDifficulty.asStringRes(): StringResource {
    return when (this) {
        LessonDifficulty.Easy -> Res.string.easy
        LessonDifficulty.Medium -> Res.string.medium
        LessonDifficulty.Hard -> Res.string.hard
    }
}

fun LessonDifficulty.asColor(): Color {
    return when (this) {
        LessonDifficulty.Easy -> Color(127, 180, 139)
        LessonDifficulty.Medium -> Color(203, 177, 105)
        LessonDifficulty.Hard -> Color(197, 130, 130)
    }
}


fun LessonFilterType.asStringRes(): StringResource {
    return when (this) {
        LessonFilterType.OneStepQuestions -> Res.string.one_step
        LessonFilterType.StepByStep -> Res.string.step_by_step
        LessonFilterType.NotDone -> Res.string.not_done
        LessonFilterType.Tests -> Res.string.tests
    }
}
